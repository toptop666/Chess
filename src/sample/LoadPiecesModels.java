package sample;

import javafx.scene.image.Image;

import java.io.File;
import java.util.Hashtable;

public class LoadPiecesModels {

    private String directoryPath;
    private Hashtable<Class<? extends Piece>, Image> whitePieces;
    private Hashtable<Class<? extends Piece>, Image> blackPieces;


    public LoadPiecesModels(String path) {
        this.directoryPath = path;
        this.whitePieces = loadImagesFromFolder(this.directoryPath, 'l');
        this.blackPieces = loadImagesFromFolder(this.directoryPath, 'd');
    }

    private Hashtable<Class<? extends Piece>, Image> loadImagesFromFolder(String path,  char dORl) {
        Hashtable<Class<? extends Piece>, Image> piecesNamesImageHashtable = new Hashtable<Class<? extends Piece>, Image>();
        File folder = new File(path);
        File[] folderFiles = folder.listFiles();
        for (File file : folderFiles) {
            if (file.isFile()) {
                if(file.getName().charAt(1) != dORl) {
                    continue;
                }
                Image image = new Image(file.toURI().toString());
                // TODO: complete all pieces and add the classes
                switch (file.getName().charAt(0)) {
                    case 'p':
                        // pawn
                        piecesNamesImageHashtable.put(Pawn.class, image);
                        break;
                    case 'b':
                        // bishop
                        piecesNamesImageHashtable.put(Bishop.class, image);
                        break;
                    case 'n':
                        // knight
                        piecesNamesImageHashtable.put(Knight.class, image);
                        break;
                    case 'r':
                        // rook
                        piecesNamesImageHashtable.put(Rook.class, image);
                        break;
                    case 'q':
                        // queen
                        piecesNamesImageHashtable.put(Queen.class, image);
                        break;
                    case 'k':
                        // king
                        piecesNamesImageHashtable.put(King.class, image);
                        break;
                    default:
                        continue;
                }
            }
        }
        return piecesNamesImageHashtable;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public Hashtable<Class<? extends Piece>, Image> getWhitePieces() {
        return whitePieces;
    }

    public Hashtable<Class<? extends Piece>, Image> getBlackPieces() {
        return blackPieces;
    }
}
