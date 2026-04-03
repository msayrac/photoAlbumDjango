public class test {
    
    public static void main(String[] args) {
        // Metodu test etmek için çağırıyoruz
        double sonuc = hDegeriniBul(2.0, 4.0, 0.0005);
        System.out.println("Hesaplanan Derinlik (h): " + sonuc);
    }

    /**
     * Yer Değiştirme Yöntemi (False Position) ile h değerini bulan metot.
     * @param ha Alt tahmin (xl)
     * @param hu Üst tahmin (xu)
     * @param es Hedef bağıl hata sınırı (0.0005)
     * @return Hesaplanan h değeri
     */
    public static double hDegeriniBul(double ha, double hu, double es) {
        double xr = 0;       // Yeni tahmin edilen kök
        double xrEski = 0;   // Bir önceki adımdaki kök (hata hesabı için)
        double ea = 100;     // Mevcut bağıl hata (başlangıç değeri %100)
        double pi = 3.141593;
        double R = 3.0;
        double V = 60.0;

        // Iterasyon döngüsü: Bağıl hata hedeflenen toleransın altına düşene kadar devam eder
        while (ea > es) {
            xrEski = xr;

            // f(ha) ve f(hu) değerlerini hesapla
            double fha = pi * Math.pow(ha, 2) * ((3 * R - ha) / 3) - V;
            double fhu = pi * Math.pow(hu, 2) * ((3 * R - hu) / 3) - V;

            // Yer Değiştirme Formülü: xr = xu - [f(xu) * (xa - xu)] / [f(xa) - f(xu)]
            xr = hu - (fhu * (ha - hu)) / (fha - fhu);

            // Yaklaşık bağıl hata hesabı (İlk iterasyonda xrEski 0 olduğu için kontrol eklenir)
            if (xr != 0) {
                ea = Math.abs((xr - xrEski) / xr) * 100;
            }

            // Bir sonraki iterasyon için sınırları belirle
            double fxr = pi * Math.pow(xr, 2) * ((3 * R - xr) / 3) - V;

            if (fha * fxr < 0) {
                hu = xr; // Kök sol alt aralıkta
            } else {
                ha = xr; // Kök sağ üst aralıkta
            }
            
            // Hata kontrolü ve çıktı (Opsiyonel)
            // System.out.println("xr: " + xr + " | Hata: %" + ea);
        }

        return xr;
    




}
}