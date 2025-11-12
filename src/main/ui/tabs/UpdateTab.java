package ui.tabs;

import javax.swing.JButton;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import exceptions.NotInRatingRangeException;
import exceptions.PopUpClosedOrCancelledException;
import ui.ButtonNames;
import ui.ErrorMessages;
import ui.Prompts;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the update tab on the navbar of the GUI where 
// updating existing album review info is done
@ExcludeFromJacocoGeneratedReport
public class UpdateTab extends Tab {

    // EFFECTS: creates an update tab with a sidebar
    // containing buttons related to updating album review fields
    public UpdateTab() {
        super();
        createSidebar();
        addButtonsToSidebar();
        setVisible(true);
    }

    // EFFECTS: adds all buttons related to updating album review operations to the
    // sidebar
    private void addButtonsToSidebar() {
        createUpdateNameButton();
        createUpdateArtistButton();
        createUpdateGenreButton();
        createUpdateRatingButton();
        createUpdateReviewButton();
    }

    // EFFECTS: prompts user for an album (referenced by name and artist) and new
    // name, then updates that album to have the new name
    private void createUpdateNameButton() {
        JButton button = createButton(ButtonNames.UPDATE_NAME.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());
                String newName = getUserInput(Prompts.NEW_ALBUM_NAME.getValue());

                if (manager.getWantedAlbum(name, artist) != null) {
                    manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(name, artist)))
                            .setName(newName);
                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing
            }
        });
        addToSidebar(button);
    }

    // EFFECTS: prompts user for an album (referenced by name and artist) and new
    // artist, then updates that album to have the new artist
    private void createUpdateArtistButton() {
        JButton button = createButton(ButtonNames.UPDATE_ARTIST.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {

            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());
                String newArtist = getUserInput(Prompts.NEW_ARTIST.getValue());

                if (manager.getWantedAlbum(name, artist) != null) {
                    manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(name, artist)))
                            .setArtist(newArtist);
                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing
            }
        });
        addToSidebar(button);
    }

    // EFFECTS: prompts user for an album (referenced by name and artist) and new
    // genre, then updates that album to have the new genre
    private void createUpdateGenreButton() {
        JButton button = createButton(ButtonNames.UPDATE_GENRE.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {

            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());
                String newGenre = getUserInput(Prompts.NEW_GENRE.getValue());

                if (manager.getWantedAlbum(name, artist) != null) {
                    manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(name, artist)))
                            .setGenre(newGenre);
                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing
            }
        });
        addToSidebar(button);
    }

    // EFFECTS: prompts user for an album (referenced by name and artist) and new
    // rating, then updates that album to have the new rating. if the given rating
    // is not between 0.0 and 10.0 inclusive then throw NotInRatingRangeException()
    // and handle it by displaying an error message
    private void createUpdateRatingButton() {
        JButton button = createButton(ButtonNames.UPDATE_RATING.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {

            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());
                Double newRating = Double.parseDouble(getUserInput(Prompts.NEW_RATING.getValue()));

                if (!(newRating >= 0.0 && newRating <= 10.0)) {
                    throw new NotInRatingRangeException();
                }

                newRating = Math.round(newRating * 10.0) / 10.0;

                if (manager.getWantedAlbum(name, artist) != null) {
                    manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(name, artist)))
                            .setRating(newRating);
                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (NumberFormatException | NotInRatingRangeException excpetion) {
                showErrorMessage(this, ErrorMessages.INVALID.getValue());
            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing
            }
        });
        addToSidebar(button);
    }

    // EFFECTS: prompts user for an album (referenced by name and artist) and new
    // review, then updates that album to have the new artist
    private void createUpdateReviewButton() {
        JButton button = createButton(ButtonNames.UPDATE_REVIEW.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());
                String newReview = getUserInput(Prompts.NEW_REVIEW.getValue());

                if (manager.getWantedAlbum(name, artist) != null) {
                    manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(name, artist)))
                            .setReview(newReview);
                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing
            }
        });
        addToSidebar(button);

    }

}