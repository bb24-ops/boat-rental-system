/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Iznajmljivanje;

import domen.Administrator;
import domen.Brod;
import domen.Iznajmljivanje;
import domen.Korisnik;
import domen.Skiper;
import domen.SkiperDez;
import domen.StavkaIzn;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.Iznajmljivanje.DodajIznajmljivanjeFormu;
import forme.administrator.DodajAdministratoraForma;
import forme.model.ModelTabeleStavkaIzn;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import kontroleri.GlavnaFormaController;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class DodajIznajmljivanjeController {

    private final DodajIznajmljivanjeFormu dif;

    public DodajIznajmljivanjeController(DodajIznajmljivanjeFormu dif) {
        this.dif = dif;

        dif.pack();
        dif.setLocationRelativeTo(null);
        dif.setResizable(false);
        dif.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        dif.setIconImage(icon);

        addActionListeners();
        dodajDocumentListenereZaDatume();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dif.setVisible(true);
    }

    private void addActionListeners() {
        dif.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    Brod b = (Brod) dif.getjComboBoxBrod().getSelectedItem();
                    String datumPreuzimanjaString = dif.getjTextFieldDatumPreuzimanja().getText().trim();
                    String datumPovratkaString = dif.getjTextFieldDatumPovratka().getText().trim();

                    if (b == null || b.getIdBrod() <= 0) {
                        JOptionPane.showMessageDialog(dif, "Sistem ne moze da kreira stavku.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (datumPreuzimanjaString.isEmpty() || datumPovratkaString.isEmpty()) {
                        JOptionPane.showMessageDialog(dif, "Sistem ne moze da kreira stavku.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LocalDate ldDatumPreuzimanja = LocalDate.parse(datumPreuzimanjaString);
                    LocalDate ldDatumPovratka = LocalDate.parse(datumPovratkaString);

                    long brojDana = ChronoUnit.DAYS.between(ldDatumPreuzimanja, ldDatumPovratka);
                    if (brojDana < 0) {
                        JOptionPane.showMessageDialog(dif, "Datum povratka ne može biti pre datuma preuzimanja!", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (brojDana == 0) {
                        brojDana = 1;
                    } else if (brojDana > 0) {
                        brojDana = brojDana + 1;
                    }

                    if (brojDana <= 0) {
                        JOptionPane.showMessageDialog(dif, "Sistem ne moze da kreira stavku.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double iznosJedneStavke = brojDana * b.getCenaPoDanu();
                    if (iznosJedneStavke <= 0) {
                        JOptionPane.showMessageDialog(dif, "Sistem ne moze da kreira stavku.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Konverzija LocalDate → java.util.Date
                    Date datumPreuzimanja = Date.from(ldDatumPreuzimanja.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date datumPovratka = Date.from(ldDatumPovratka.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    StavkaIzn stavka = new StavkaIzn();
                    stavka.setBrod(b);
                    stavka.setDatumIzdavanja(datumPreuzimanja);
                    stavka.setDatumPovratka(datumPovratka);
                    stavka.setBrojDana((int) brojDana);
                    stavka.setIznosJedneStavke(iznosJedneStavke);

                    ModelTabeleStavkaIzn mts = (ModelTabeleStavkaIzn) dif.getjTable1().getModel();
                    mts.dodajStavku(stavka);

                   
                    double ukupnaCena = 0;

                    for (StavkaIzn s : mts.getLista()) {
                        ukupnaCena += s.getIznosJedneStavke();
                    }
                    dif.getjLabelUkupnaCena().setText("Ukupna cena: " + ukupnaCena + " RSD");

                    dif.getjTextFieldDatumPreuzimanja().setText("");
                    dif.getjTextFieldDatumPovratka().setText("");
                    dif.getjTextFieldCenaZaDan().setText(b.getCenaPoDanu() + "");

                    JOptionPane.showMessageDialog(dif, "Sistem je kreirao stavku.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    dif.getjCheckBoxSkiper().setSelected(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dif, "Sistem ne moze da kreira stavku.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dif.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int red = dif.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(dif, "Sistem ne moze da obrise stavku.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dif, "Sistem je obrisao izabranu stavku.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleStavkaIzn mts = (ModelTabeleStavkaIzn) dif.getjTable1().getModel();
                    StavkaIzn si = mts.getLista().get(red);
                    mts.obrisiStavku(si);
                    
                    //osveziUkupnuCenu();
                    double ukupnaCena = 0;
                    for (StavkaIzn s : mts.getLista()) {
                        ukupnaCena += s.getIznosJedneStavke();
                    }

                    dif.getjLabelUkupnaCena().setText("Ukupna cena: " + ukupnaCena + " RSD");
                }
            }

        });

        dif.getjComboBoxBrod().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Brod b = (Brod) e.getItem();
                    dif.getjTextFieldCenaZaDan().setText(String.valueOf(b.getCenaPoDanu()));
                }
            }
        });

        dif.dodajIznajmljivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodaj(e);
                } catch (Exception ex) {
                    Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void dodaj(ActionEvent e) throws Exception {
                try {
                    Iznajmljivanje i = new Iznajmljivanje();

                    ModelTabeleStavkaIzn mts = (ModelTabeleStavkaIzn) dif.getjTable1().getModel();
                    List<StavkaIzn> stavke = mts.getLista();
                    i.setStavke(stavke);
                    if (stavke == null || stavke.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double ukupanIznos = 0;
                    for (StavkaIzn s : stavke) {
                        ukupanIznos += s.getIznosJedneStavke();
                    }
                    if (ukupanIznos <= 0) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setUkupanIznos(ukupanIznos);

                    if (Kordinator.getInstance().getUlogovani() == null
                            || Kordinator.getInstance().getUlogovani().getIdAdministrator() <= 0) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setAdministrator(Kordinator.getInstance().getUlogovani());

                    Korisnik k = (Korisnik) dif.getjComboBoxKorisnici().getSelectedItem();
                    if (k == null || k.getIdKorisnik() <= 0) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setKorisnik(k);

                    String datumString = dif.getjTextFieldDatum().getText().trim();
                    if (datumString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

                    if (!datumString.matches(regex)) {
                        JOptionPane.showMessageDialog(null, "Datum mora biti unesen u formatu yyyy-MM-dd (npr. 2025-08-23).", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false);
                    Date datum;
                    try {
                        datum = sdf.parse(datumString);
                    } catch (Exception exp) {
                        JOptionPane.showMessageDialog(null, "Neispravan datum! Proverite unos.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    //dozvoljavam i danasnji dan
                    LocalDate danas = LocalDate.now();
                    LocalDate datumLD = datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    if (datumLD.isBefore(danas)) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    i.setDatum(datum);

                    if (dif.getjCheckBoxSkiper().isSelected()) {
                        Skiper s = (Skiper) dif.getjComboBoxSkiperi().getSelectedItem();
                        if (s == null || s.getIdSkiper() <= 0) {
                            JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti dezurstvo skipera.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        SkiperDez sd = new SkiperDez();
                        sd.setAdministrator(Kordinator.getInstance().getUlogovani());
                        sd.setDatumDezurstva(datum);
                        sd.setSkiper(s);
                        try {
                            komunikacija.Komunikacija.getInstanca().dodajSkiperDerzurstvo(sd);
                            JOptionPane.showMessageDialog(null, "Sistem je zapamtio dezurstvo skipera.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti dezurstvo skipera.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    // --- CUVANJE ---
                    try {
                        komunikacija.Komunikacija.getInstanca().kreirajIznajmljivanje(i);
                        Kordinator.getInstance().osveziFormuPrikazIznajmljivanja();
                        JOptionPane.showMessageDialog(null, "Sistem je zapamtio iznajmljivanje.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        dif.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        dif.izmeniIznajmljivanjeAddActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    //validacija!
                    izmeni(e);
                } catch (Exception ex) {
                    Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void izmeni(ActionEvent e) throws Exception {
                try {
                    Iznajmljivanje i = new Iznajmljivanje();

                    int id = Integer.parseInt(dif.getjTextFieldID().getText());
                    i.setIdIznajmljivanje(id);

                    ModelTabeleStavkaIzn mts = (ModelTabeleStavkaIzn) dif.getjTable1().getModel();
                    List<StavkaIzn> stavke = mts.getLista();

                    if (stavke == null || stavke.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setStavke(stavke);

                    double ukupanIznos = 0;
                    for (StavkaIzn s : stavke) {
                        ukupanIznos += s.getIznosJedneStavke();
                    }
                    if (ukupanIznos <= 0) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setUkupanIznos(ukupanIznos);

                    if (Kordinator.getInstance().getUlogovani() == null || Kordinator.getInstance().getUlogovani().getIdAdministrator() <= 0) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setAdministrator(Kordinator.getInstance().getUlogovani());

                    Korisnik k = (Korisnik) dif.getjComboBoxKorisnici().getSelectedItem();
                    if (k == null || k.getIdKorisnik() <= 0) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setKorisnik(k);

                    String datumString = dif.getjTextFieldDatum().getText().trim();
                    if (datumString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date datum = sdf.parse(datumString);

                    // Dozvoljen i današnji datum
                    Date danas = new Date();
                    if (datum.before(danas) && !isIstiDan(datum, danas)) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i.setDatum(datum);

                    if (dif.getjCheckBoxSkiper().isSelected()) {
                        Skiper s = (Skiper) dif.getjComboBoxSkiperi().getSelectedItem();
                        if (s == null || s.getIdSkiper() <= 0) {
                            JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti dezurstvo skipera.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        SkiperDez sd = new SkiperDez();
                        sd.setAdministrator(Kordinator.getInstance().getUlogovani());
                        sd.setDatumDezurstva(datum);
                        sd.setSkiper(s);
                        try {
                            komunikacija.Komunikacija.getInstanca().dodajSkiperDerzurstvo(sd);
                            JOptionPane.showMessageDialog(null, "Sistem je zapamtio dezurstvo skipera.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti dezurstvo skipera.", "Upozorenje!", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    // --- CUVANJE ---
                    try {
                        komunikacija.Komunikacija.getInstanca().izmeniIznajmljivanje(i);
                        Kordinator.getInstance().osveziObeTabele();
                        JOptionPane.showMessageDialog(null, "Sistem je zapamtio iznajmljivanje", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        dif.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean isIstiDan(Date d1, Date d2) {
                // Proverava da li su datumi isti dan
                SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                return fmt.format(d1).equals(fmt.format(d2));
            }
        });

        dif.getjComboBoxBrod().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Brod b = (Brod) e.getItem();

                    // Prikaži cenu po danu
                    dif.getjTextFieldCenaZaDan().setText(String.valueOf(b.getCenaPoDanu()));

                    // Uzmi broj dana iz textField-a
                    String brojDanaStr = dif.getjTextFieldBrojDana().getText().trim();
                    int brojDana = 0;
                    try {
                        brojDana = Integer.parseInt(brojDanaStr);
                    } catch (NumberFormatException ex) {
                        // Ako nije unet broj ili je prazan, ostavi brojDana kao 0
                    }

                    // Izračunaj ukupnu cenu i postavi u textField
                    double ukupnaCena = brojDana * b.getCenaPoDanu();
                    dif.getjTextField1().setText(ukupnaCena + "");
                }
            }
        });

        

        dif.getjCheckBoxSkiper().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    dif.getjComboBoxSkiperi().setEnabled(true);
                }
            }
        });

        dif.OtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi(e);
            }

            private void otkazi(ActionEvent e) {
                dif.dispose();
            }
        });

    }

    private void pripremiFormu(FormaMod mod) {
        popuniComboBoxeve();

        dif.getjComboBoxSkiperi().setEnabled(false);
        List<StavkaIzn> praznaLista = new ArrayList<>();
        ModelTabeleStavkaIzn mts = new ModelTabeleStavkaIzn(praznaLista);
        dif.getjTable1().setModel(mts);

        switch (mod) {
            case DODAJ:
                dif.getjButtonIzmeniRacun().setVisible(false);
                dif.getjButtonKreirajRacun().setVisible(true);
                dif.getjButtonKreirajRacun().setEnabled(true);
                dif.getjTextFieldID().setEnabled(false);
                JOptionPane.showMessageDialog(null, "Sistem je kreirao iznajmljvanje", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                break;
            case IZMENI:

                dif.getjButtonKreirajRacun().setVisible(false);
                dif.getjButtonIzmeniRacun().setVisible(true);
                dif.getjButtonIzmeniRacun().setEnabled(true);
                dif.getjTextFieldID().setEnabled(false);

                Iznajmljivanje i = (Iznajmljivanje) Kordinator.getInstance().vratiParam("iznajmljivanje_za_izmenu");
                mts.setLista(i.getStavke());

                dif.getjTextFieldID().setText(i.getIdIznajmljivanje() + "");
                dif.getjTextFieldDatum().setText(i.getDatum() + "");
                dif.getjComboBoxKorisnici().setSelectedItem(i.getKorisnik());
                dif.getjLabelUkupnaCena().setText("Ukupna cena: " + i.getUkupanIznos() + "RSD");
                
                JOptionPane.showMessageDialog(dif, "Sistem je nasao iznajmljivanje", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                throw new AssertionError();
        }
    }

    public void popuniComboBoxeve() {
        List<Korisnik> sviKorisnici = komunikacija.Komunikacija.getInstanca().ucitajKorisnike();

        dif.getjComboBoxKorisnici().removeAllItems();
        for (Korisnik k : sviKorisnici) {
            dif.getjComboBoxKorisnici().addItem(k);
        }

        List<Brod> sviBrodovi = komunikacija.Komunikacija.getInstanca().ucitajBrodove();
        dif.getjComboBoxBrod().removeAllItems();
        for (Brod b : sviBrodovi) {
            dif.getjComboBoxBrod().addItem(b);
        }

        List<Skiper> sviSkiper = komunikacija.Komunikacija.getInstanca().ucitajSkipere();
        dif.getjComboBoxSkiperi().removeAllItems();
        for (Skiper s : sviSkiper) {
            dif.getjComboBoxSkiperi().addItem(s);
        }
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private void dodajDocumentListenereZaDatume() {
        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                izracunajBrojDana();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                izracunajBrojDana();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                izracunajBrojDana();
            }
        };

        dif.getjTextFieldDatumIzdavanja().getDocument().addDocumentListener(docListener);
        dif.getjTextFieldDatumPovratka().getDocument().addDocumentListener(docListener);
    }

    private void izracunajBrojDana() {
        String datumIzdavanjaStr = dif.getjTextFieldDatumIzdavanja().getText().trim();
        String datumPovratkaStr = dif.getjTextFieldDatumPovratka().getText().trim();

        if (datumIzdavanjaStr.isEmpty() || datumPovratkaStr.isEmpty()) {
            dif.getjTextFieldBrojDana().setText("");
            dif.getjTextField1().setText("");
            return;
        }

        try {
            LocalDate datumIzdavanja = LocalDate.parse(datumIzdavanjaStr, formatter);
            LocalDate datumPovratka = LocalDate.parse(datumPovratkaStr, formatter);

            if (datumPovratka.isBefore(datumIzdavanja)) {
                dif.getjTextFieldBrojDana().setText("Greška, povratak je pre izdavanja");
                dif.getjTextField1().setText("");
                return;
            }

            long brojDana = ChronoUnit.DAYS.between(datumIzdavanja, datumPovratka);
            if (brojDana == 0) {
                brojDana = 1; // bar jedan dan
            } else if (brojDana > 0) {
                brojDana = (int) brojDana + 1;
            }
            dif.getjTextFieldBrojDana().setText(String.valueOf(brojDana));
            azurirajIznosStavke();
        } catch (Exception ex) {
            dif.getjTextFieldBrojDana().setText("Nevalidan datum");
            dif.getjTextField1().setText("");
        }
    }

    private void azurirajIznosStavke() {
        try {
            String brojDanaStr = dif.getjTextFieldBrojDana().getText().trim();
            if (brojDanaStr.isEmpty()) {
                dif.getjTextField1().setText("");
                return;
            }
            int brojDana = Integer.parseInt(brojDanaStr);

            Brod b = (Brod) dif.getjComboBoxBrod().getSelectedItem();
            if (b == null) {
                dif.getjTextField1().setText("");
                return;
            }

            double cena = brojDana * b.getCenaPoDanu();
            dif.getjTextField1().setText(cena + "");
        } catch (NumberFormatException ex) {
            dif.getjTextField1().setText("Nevalidan broj dana");
        }
    }

}
