namespace HVK.Models {
    public class ReservationViewModel {
        public Hvkuser Hvkuser { get; set; }
        public Reservation Reservation { get; set; }
        public List<PetVaccinationViewModel> PetVaccinationViewModels { get; set; }

        public ReservationViewModel () {
            PetVaccinationViewModels = new List<PetVaccinationViewModel>();
        }
    }
}
