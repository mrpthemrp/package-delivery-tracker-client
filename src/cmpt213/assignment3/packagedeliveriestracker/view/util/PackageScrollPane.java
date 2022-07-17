package cmpt213.assignment3.packagedeliveriestracker.view.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PackageScrollPane extends JScrollPane {
    private final int width;
    private final int height;
    private JButton listAllBtn, overdueBtn, upcomingBtn;
    private JLabel listAllText, overdueText, upcomingText;
    private final JPanel basePanel;
    private static final JPanel listAllCard = new JPanel();
    private static final JPanel overdueCard = new JPanel();
    private static final JPanel upcomingCard = new JPanel();
    private final JPanel columnHeader;

    public PackageScrollPane(int width, int height, ActionListener al) {

        this.width = width;
        this.height = height;
        this.basePanel = new JPanel(new CardLayout());
        this.columnHeader = new JPanel();

        basePanel.setSize(new Dimension((width), (height * 2)));
        basePanel.setMaximumSize(new Dimension(width, (height * 2)));
        basePanel.setBackground(Color.blue);
        basePanel.setBorder(new LineBorder(Color.BLACK, 5));

        setUpViews(al);
        addCards();

        this.setViewportView(basePanel);
    }

    private void setUpViews(ActionListener al) {
        setUpListAll(al);
        setUpUpcoming(al);
        setUpOverdue(al);

        //set up column header
        this.columnHeader.setLayout(new BoxLayout(columnHeader, BoxLayout.LINE_AXIS));
        this.columnHeader.add(listAllBtn);
        this.columnHeader.add(overdueBtn);
        this.columnHeader.add(upcomingBtn);
    }

    private void addCards() {
        basePanel.add(listAllCard);
        basePanel.add(overdueCard);
        basePanel.add(upcomingCard);

        this.setRowHeaderView(columnHeader);
    }

    private void setUpListAll(ActionListener al) {
        this.listAllText = new JLabel("LIST OF ALL PACKAGES");
        this.listAllCard.setLayout(new BoxLayout(listAllCard, BoxLayout.Y_AXIS));
        this.listAllBtn = new JButton("LIST ALL");
        this.listAllBtn.setActionCommand("LIST ALL");
        this.listAllBtn.addActionListener(al);

        this.listAllCard.add(new PackageItem(), Box.CENTER_ALIGNMENT);
    }

    private void setUpUpcoming(ActionListener al) {
        this.upcomingText = new JLabel("UPCOMING PACKAGES");
        this.upcomingCard.setLayout(new BoxLayout(upcomingCard, BoxLayout.Y_AXIS));
        this.upcomingBtn = new JButton("UPCOMING");
        this.upcomingBtn.setActionCommand("UPCOMING");
        this.upcomingBtn.addActionListener(al);

        this.upcomingCard.add(new PackageItem(), Box.CENTER_ALIGNMENT);
    }

    private void setUpOverdue(ActionListener al) {
        this.overdueText = new JLabel("OVERDUE PACKAGES");
        this.overdueCard.setLayout(new BoxLayout(overdueCard, BoxLayout.Y_AXIS));
        this.overdueBtn = new JButton("OVERDUE");
        this.overdueBtn.setActionCommand("OVERDUE");

        this.overdueBtn.add(new PackageItem(), Box.CENTER_ALIGNMENT);
    }

    public static void switchView(Util.SCREEN_STATE newState) {

        switch (newState) {
            case LIST_ALL -> {
                listAllCard.setVisible(true);
                upcomingCard.setVisible(false);
                overdueCard.setVisible(false);
            }
            case UPCOMING -> {
                listAllCard.setVisible(false);
                upcomingCard.setVisible(true);
                overdueCard.setVisible(false);
            }
            case OVERDUE -> {
                listAllCard.setVisible(false);
                upcomingCard.setVisible(false);
                overdueCard.setVisible(true);
            }
        }

    }
}
