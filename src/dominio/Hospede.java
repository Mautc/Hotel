    package dominio;

    import java.time.LocalDate;

    public class Hospede {
        private String nomehospede;
        private LocalDate dataCheckin;
        private LocalDate dataCheckout;
        private int numQuartosReservados;
        private int tipoQuartoReservado;

        public Hospede(String nomehospede, LocalDate dataCheckin, LocalDate dataCheckout, int numQuartosReservados, int tipoQuartoReservado) {
            this.nomehospede = nomehospede;
            this.dataCheckin = dataCheckin;
            this.dataCheckout = dataCheckout;
            this.numQuartosReservados = numQuartosReservados;
            this.tipoQuartoReservado = tipoQuartoReservado;
        }

        public String getNomehospede() {
            return nomehospede;
        }

        public void setNomehospede(String nomehospede) {
            this.nomehospede = nomehospede;
        }

        public LocalDate getDataCheckin() {
            return dataCheckin;
        }

        public void setDataCheckin(LocalDate dataCheckin) {
            this.dataCheckin = dataCheckin;
        }

        public LocalDate getDataCheckout() {
            return dataCheckout;
        }

        public void setDataCheckout(LocalDate dataCheckout) {
            this.dataCheckout = dataCheckout;
        }

        public int getNumQuartosReservados() {
            return numQuartosReservados;
        }

        public void setNumQuartosReservados(int numQuartosReservados) {
            this.numQuartosReservados = numQuartosReservados;
        }

        public int getTipoQuartoReservado() {
            return tipoQuartoReservado;
        }

        public void setTipoQuartoReservado(int tipoQuartoReservado) {
            this.tipoQuartoReservado = tipoQuartoReservado;
        }

        @Override
        public String toString() {
            return "Hospede{" +
                    "nomehospede='" + nomehospede + '\'' +
                    ", dataCheckin=" + dataCheckin +
                    ", dataCheckout=" + dataCheckout +
                    ", numQuartosReservados=" + numQuartosReservados +
                    ", tipoQuartoReservado=" + tipoQuartoReservado +
                    '}';
        }
    }
