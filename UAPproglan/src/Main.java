import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Kelas utama untuk tampilan GUI dengan Swing
class AplikasiATM extends JFrame {
    private ATM atm;
    private JTextField fieldPin;
    private JLabel labelSaldo;

    // Konstruktor AplikasiATM
    public AplikasiATM() {
        atm = new ATM();

        // Atur properti frame
        setTitle("Sistem ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Buat komponen UI
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        fieldPin = new JTextField(10);
        JButton tombolLogin = new JButton("Masuk");

        // Tambahkan komponen ke panel
        panel.add(new JLabel("Masukkan PIN:"));
        panel.add(fieldPin);
        panel.add(tombolLogin);

        // Tambahkan panel ke frame
        add(panel);

        // Aksi tombol login
        tombolLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pin = fieldPin.getText();
                try {
                    atm.masuk(pin);
                    tampilkanMenu();
                } catch (PinTidakValidException ex) {
                    JOptionPane.showMessageDialog(AplikasiATM.this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Metode untuk menampilkan menu setelah login
    private void tampilkanMenu() {
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        JButton tombolCekSaldo = new JButton("Cek Saldo");
        JButton tombolTarikTunai = new JButton("Tarik Tunai");
        JButton tombolTransfer = new JButton("Transfer");
        JButton tombolTambahSaldo = new JButton("Tambah Saldo");
        JButton tombolMutasi = new JButton("Mutasi Transaksi");
        JButton tombolKeluar = new JButton("Keluar");

        // Aksi untuk tombol-tombol
        tombolCekSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double saldo = atm.getSaldo();
                JOptionPane.showMessageDialog(AplikasiATM.this, "Saldo Anda: Rp" + saldo, "Informasi Saldo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        tombolTarikTunai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(AplikasiATM.this, "Masukkan jumlah yang ingin ditarik:");
                try {
                    double jumlah = Double.parseDouble(input);
                    atm.tarikTunai(jumlah);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AplikasiATM.this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tombolTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(AplikasiATM.this, "Masukkan jumlah yang ingin ditransfer:");
                try {
                    double jumlah = Double.parseDouble(input);
                    String penerima = JOptionPane.showInputDialog(AplikasiATM.this, "Masukkan nama penerima:");
                    atm.transfer(jumlah, penerima);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AplikasiATM.this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tombolTambahSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(AplikasiATM.this, "Masukkan jumlah yang ingin ditambahkan:");
                try {
                    double jumlah = Double.parseDouble(input);
                    atm.tambahSaldo(jumlah);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AplikasiATM.this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tombolMutasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> mutasi = atm.getMutasi();
                StringBuilder pesan = new StringBuilder("Mutasi Transaksi:\n");
                for (String transaksi : mutasi) {
                    pesan.append(transaksi).append("\n");
                }
                JOptionPane.showMessageDialog(AplikasiATM.this, pesan.toString(), "Mutasi Transaksi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        tombolKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Keluar dari aplikasi
            }
        });

        // Tambahkan tombol ke panel
        panelMenu.add(tombolCekSaldo);
        panelMenu.add(tombolTarikTunai);
        panelMenu.add(tombolTransfer);
        panelMenu.add(tombolTambahSaldo);
        panelMenu.add(tombolMutasi);
        panelMenu.add(tombolKeluar);

        // Ganti panel dengan panel menu
        getContentPane().removeAll();
        add(panelMenu);
        revalidate();
        repaint();
    }

    // Metode utama untuk menjalankan aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplikasiATM().setVisible(true);
            }
        });
    }
}

// Kelas ATM untuk menangani logika transaksi
class ATM {
    private Pengguna pengguna;

    public ATM() {
        // Inisialisasi dengan pengguna default (PIN: "1812", Saldo: Rp0)
        this.pengguna = new Pengguna("1812", 0);
    }

    // Metode login
    public void masuk(String pin) throws PinTidakValidException {
        if (!pengguna.validasiPin(pin)) {
            throw new PinTidakValidException("PIN tidak valid");
        }
    }

    // Dapatkan saldo saat ini
    public double getSaldo() {
        return pengguna.getSaldo();
    }

    // Tarik tunai
    public void tarikTunai(double jumlah) throws SaldoTidakCukupException {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah harus positif.");
        }
        if (pengguna.getSaldo() < jumlah) {
            throw new SaldoTidakCukupException("Saldo tidak cukup.");
        }
        pengguna.setSaldo(pengguna.getSaldo() - jumlah);
        pengguna.tambahMutasi("Tarik Tunai: -Rp" + jumlah);
    }

    // Transfer uang ke akun lain
    public void transfer(double jumlah, String penerima) throws SaldoTidakCukupException {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah harus positif.");
        }
        if (pengguna.getSaldo() < jumlah) {
            throw new SaldoTidakCukupException("Saldo tidak cukup.");
        }
        pengguna.setSaldo(pengguna.getSaldo() - jumlah);
        pengguna.tambahMutasi("Transfer ke " + penerima + ": -Rp" + jumlah);
        // Simulasi transfer ke penerima
    }

    // Tambah saldo
    public void tambahSaldo(double jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah harus positif.");
        }
        pengguna.setSaldo(pengguna.getSaldo() + jumlah);
        pengguna.tambahMutasi("Tambah Saldo: +Rp" + jumlah);
    }

    // Ambil daftar mutasi
    public List<String> getMutasi() {
        return pengguna.getMutasi();
    }
}

// Kelas Pengguna untuk menyimpan data pengguna (PIN, Saldo, dan Mutasi)
class Pengguna {
    private String pin;
    private double saldo;
    private List<String> mutasi;

    public Pengguna(String pin, double saldo) {
        this.pin = pin;
        this.saldo = saldo;
        this.mutasi = new ArrayList<>();
    }

    public boolean validasiPin(String pin) {
        return this.pin.equals(pin);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void tambahMutasi(String transaksi) {
        mutasi.add(transaksi);
    }

    public List<String> getMutasi() {
        return mutasi;
    }
}

// Exception untuk PIN tidak valid
class PinTidakValidException extends Exception {
    public PinTidakValidException(String pesan) {
        super(pesan);
    }
}

// Exception untuk saldo tidak cukup
class SaldoTidakCukupException extends Exception {
    public SaldoTidakCukupException(String pesan) {
        super(pesan);
    }
}
