using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel;
using static HVK.Models.CustomValidation;

namespace HVK.Models
{
    public partial class Reservation
    {
        public Reservation()
        {
            PetReservations = new HashSet<PetReservation>();
            ReservationDiscounts = new HashSet<ReservationDiscount>();
        }

        public int ReservationId { get; set; }
        [Required(ErrorMessage = "Please enter a start date.")]
        [DisplayName("*Start Date:")]
        [DataType(DataType.Date)]
        [RequiredDate(ErrorMessage = "Please enter a start date.")]
        [StartDate(ErrorMessage = "Start date must be after today.")]
        public DateTime StartDate { get; set; }
        [Required(ErrorMessage = "Please enter an end date.")]
        [DisplayName("*End Date:")]
        [DataType(DataType.Date)]
        [RequiredDate(ErrorMessage = "Please enter an end date.")]
        [EndDate(StartDatePropertyName = "StartDate", ErrorMessage = "End date must be after the start date.")]
        public DateTime EndDate { get; set; }
        public decimal Status { get; set; }

        public virtual ICollection<PetReservation> PetReservations { get; set; }
        public virtual ICollection<ReservationDiscount> ReservationDiscounts { get; set; }
    }
}
