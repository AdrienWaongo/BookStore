
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MediaRentalSystemGUI extends JFrame {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem loadMenuItem, findMenuItem, rentMenuItem, quitMenuItem;

    public MediaRentalSystemGUI() {
        setTitle("Welcome Media Rental System");
        setBounds (400, 200, 500, 300) ;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // création de la barre de menus
        menuBar = new JMenuBar();

        // création du menu
        menu = new JMenu("Menu");

        // création des items de menu
        loadMenuItem = new JMenuItem("Load Media objects...");
        findMenuItem = new JMenuItem("Find Media object...");
        rentMenuItem = new JMenuItem("Rent Media object...");
        quitMenuItem = new JMenuItem("Quit");

        // ajout des items de menu au menu
        menu.add(loadMenuItem);
        menu.add(findMenuItem);
        menu.add(rentMenuItem);
        menu.add(quitMenuItem);

        // ajout du menu à la barre de menus
        menuBar.add(menu);

        // ajout de la barre de menus à la fenêtre
        setJMenuBar(menuBar);

        // ajout de l'action listener pour l'item "Load Media objects"
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(MediaRentalSystemGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File directory = fileChooser.getSelectedFile();
                    List<String> mediaFiles = getMediaFiles(directory);
                    displayMediaFiles(mediaFiles);
                }
            }
        });

        // ajout de l'action listener pour l'item "Find Media object"
        findMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(MediaRentalSystemGUI.this, "Enter the title:");
                Media foundMedia = findMedia(title);
                if (foundMedia == null) {
                    JOptionPane.showMessageDialog(MediaRentalSystemGUI.this, "There is no media with this title: " + title);
                } else {
                    JOptionPane.showMessageDialog(MediaRentalSystemGUI.this, foundMedia.toString());
                }
            }
        });
        // ajout de l'action listener pour l'item "Rent Media object"
        rentMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(MediaRentalSystemGUI.this, "Enter the ID:");
                if (searchTerm != null && !searchTerm.isEmpty()) {
                    List<File> matchingFiles = findMediaFiles(searchTerm);
                    if (!matchingFiles.isEmpty()) {
                        // Display list of matching files and allow user to rent one
                        // (implementation details depend on the requirements of the system)
                    } else {
                        JOptionPane.showMessageDialog(MediaRentalSystemGUI.this, "No matching Media Objects found.");
                    }
                }
            }
        });

        // ajout de l'action listener pour l'item "Quit"
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MediaRentalSystemGUI.this,
                        "Are you sure you want to quit?", "Quit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        setVisible(true);
    }
    private List<File> findMediaFiles(String searchTerm) {
        List<File> matchingFiles = new ArrayList<>();
        File[] roots = File.listRoots(); // get all available file system roots
        for (File root : roots) {
            searchForMediaFiles(root, searchTerm, matchingFiles);
        }
        return matchingFiles;
    }
    private void searchForMediaFiles(File dir, String searchTerm, List<File> matchingFiles) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchForMediaFiles(file, searchTerm, matchingFiles); // recursively search subdirectories
                } else if (isMediaFile(file.getName())) {
                    if (searchTerm == null || searchTerm.isEmpty() || file.getName().contains(searchTerm)) {
                        matchingFiles.add(file);
                    }
                }
            }
        }
    }
    public abstract class Media {
        private int id;
        private String title;
        private int year;
        private boolean available;

        public Media(int id, String title, int year, boolean available) {
            this.id = id;
            this.title = title;
            this.year = year;
            this.available = available;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getYear() {
            return year;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Media [ id=" + id + ", title=" + title + ", year=" + year + ", available=" + available + "]";
        }
    }

    public class EBook extends Media {
        private int chapters;

        public EBook(int id, String title, int year, boolean available, int chapters) {
            super(id, title, year, available);
            this.chapters = chapters;
        }

        public int getChapters() {
            return chapters;
        }

        @Override
        public String toString() {
            return "EBook " + super.toString() + " [chapters=" + chapters + "]";
        }
    }

    public class MovieDVD extends Media {
        private double size;

        public MovieDVD(int id, String title, int year, boolean available, double size) {
            super(id, title, year, available);
            this.size = size;
        }

        public double getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "MovieDVD " + super.toString() + " [size=" + size + "MB]";
        }
    }

    private List<String> getMediaFiles(File directory) {
        List<String> mediaFiles = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile() && isMediaFile(file.getName())) {
                mediaFiles.add(file.getName());
            }
        }
        return mediaFiles;
    }

    private boolean isMediaFile(String fileName) {
        return fileName.endsWith(".mp4") || fileName.endsWith(".avi") || fileName.endsWith(".mkv");
    }

    private void displayMediaFiles(List<String> mediaFiles) {
        StringBuilder sb = new StringBuilder();
        sb.append("Media Files:\n");
        for (String mediaFile : mediaFiles) {
            sb.append(mediaFile).append("\n");
        }
        JOptionPane.showMessageDialog(MediaRentalSystemGUI.this, sb.toString());
    }
    public class MediaRentalSystem {
        private static List<Media> mediaList = new ArrayList<>();

        public static List<Media> getMediaList() {
            return mediaList;
        }

        public static void addMedia(Media media) {
            mediaList.add(media);
        }
    }



    private Media findMedia(String title) {
        List<Media> mediaList = MediaRentalSystem.getMediaList();
        for (Media media : mediaList) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MediaRentalSystemGUI gui = new MediaRentalSystemGUI();
    }
}