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
    private final JPanel basePanel, listAllCard, overdueCard, upcomingCard, columnHeader;
    public PackageScrollPane(int width, int height, ActionListener al) {

        this.width = width;
        this.height = height;
        this.basePanel = new JPanel(new CardLayout());
        this.columnHeader = new JPanel();
        columnHeader.setLayout(new BoxLayout(columnHeader, BoxLayout.LINE_AXIS));

        this.listAllCard = new JPanel();
        this.listAllCard.setLayout(new BoxLayout(listAllCard, BoxLayout.Y_AXIS));
        this.overdueCard = new JPanel();
        this.overdueCard.setLayout(new BoxLayout(overdueCard, BoxLayout.Y_AXIS));
        this.upcomingCard = new JPanel();
        this.upcomingCard.setLayout(new BoxLayout(upcomingCard, BoxLayout.Y_AXIS));

        this.listAllBtn = new JButton("LIST ALL");
        this.listAllBtn.setActionCommand("LIST ALL");
        this.listAllBtn.addActionListener(al);
        this.overdueBtn = new JButton("OVERDUE");
        this.overdueBtn.setActionCommand("OVERDUE");
        this.overdueBtn.addActionListener(al);
        this.upcomingBtn = new JButton("UPCOMING");
        this.upcomingBtn.setActionCommand("UPCOMING");
        this.upcomingBtn.addActionListener(al);

        listAllText = new JLabel("LIST OF ALL PACKAGES");
        overdueText = new JLabel("OVERDUE PACKAGES");
        upcomingText = new JLabel("UPCOMING PACKAGES");

        columnHeader.add(listAllBtn);
        columnHeader.add(overdueBtn);
        columnHeader.add(upcomingBtn);

        basePanel.setSize(new Dimension((width),(height*2)));
        basePanel.setMaximumSize(new Dimension(width,(height*2)));
        basePanel.setBackground(Color.blue);
        basePanel.setBorder(new LineBorder(Color.BLACK, 5));

        addCards();

        this.setViewportView(basePanel);
    }

    private void addCards() {
        basePanel.add(listAllCard);
        basePanel.add(overdueCard);
        basePanel.add(upcomingCard);

        this.setRowHeaderView(columnHeader);
    }

    private void addPackages() {

    }
}
