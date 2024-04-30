using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace HVK.Models
{
    public partial class Pet
    {
        public Pet()
        {
            PetReservations = new HashSet<PetReservation>();
            PetVaccinations = new HashSet<PetVaccination>();
        }

        public int PetId { get; set; }
        [Display(Name = "Dog Name*")]
        [DataType(DataType.Text)]
        [Required]
        [MaxLength(25)]
        [RegularExpression("^[a-zA-Z ]*$", ErrorMessage = "The Dog Name field must only contain letters.")]
        public string Name { get; set; } = null!;
        [Display(Name = "Sex*")]
        [Required(ErrorMessage = "The dog's sex is required")]
        public string Gender { get; set; } = null!;
        [Display(Name = "Breed")]
        [DataType(DataType.Text)]
        [MaxLength(50)]
        [RegularExpression("^[a-zA-Z ]*$", ErrorMessage = "The Breed field must only contain letters.")]
        public string? Breed { get; set; }
        [Display(Name = "Birth Year")]
        public int? Birthyear { get; set; }
        public int HvkuserId { get; set; }
        [Display(Name = "Size*")]
        [Required(ErrorMessage = "Please select the dog's size")]
        public string? DogSize { get; set; }
        public bool Climber { get; set; }
        public bool Barker { get; set; }
        [Display(Name = "Special Notes")]
        [DataType(DataType.MultilineText)]
        [MaxLength(200)]
        public string? SpecialNotes { get; set; }
        [Display(Name = "Sterilized*:")]
        public bool Sterilized { get; set; }

        public virtual Hvkuser Hvkuser { get; set; }
        public virtual ICollection<PetReservation> PetReservations { get; set; }
        public virtual ICollection<PetVaccination> PetVaccinations { get; set; }
    }
}
