namespace HVK.Models {
    public class PetVaccinationViewModel {
        public Pet? Pet { get; set; }
        public Dictionary<int, DateTime?>? VaccinationExpiryDates { get; set; }
    }
}
