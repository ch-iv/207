package us.jonathans.View;

import us.jonathans.InterfaceAdapters.GetLeaderboardViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetLeaderboardView extends JPanel implements ActionListener, PropertyChangeListener{
    private GetLeaderboardViewModel getLeaderboardViewModel;
    private final String viewName = "leaderboard";

    public GetLeaderboardView(GetLeaderboardViewModel getLeaderboardViewModel) {
        this.getLeaderboardViewModel = getLeaderboardViewModel;
        this.getLeaderboardViewModel.addPropertyChangeListener(this);

    }

    public String getViewName() {
        return viewName;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("leaderboard".equals(evt.getPropertyName())) {
            System.out.println("Hi");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
