package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

/*! \class public class ImageLoader
    \brief Clasa ce contine o metoda statica pentru incarcarea unei imagini in memorie.
 */
public class ImageLoader
{
    /*! \fn  public static BufferedImage loadImage(String path)
        \brief Incarca o imagine intr-un obiect BufferedImage si returneaza o referinta catre acesta.

        \param path Calea relativa pentru localizarea fisierul imagine.
     */
    public static BufferedImage LoadImage(String path)
    {
            /// Avand in vedere exista situatii in care fisierul sursa sa nu poate fi accesat
            /// metoda read() arunca o excpetie ce trebuie tratata
        try
        {
                /// Clasa ImageIO contine o serie de metode statice pentru file IO.
                /// Metoda read() are ca argument un InputStream construit avand ca referinta
                /// directorul res, director declarat ca director de resurse in care se gasesc resursele
                /// proiectului sub forma de fisiere sursa.
            return ImageIO.read(ImageLoader.class.getResource(path));
        }
        catch(IOException e)
        {
                /// Afiseaza informatiile necesare depanarii.
            e.printStackTrace();
        }
        return null;
    }}

