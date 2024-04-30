using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using static HVK.Models.CustomValidation;

namespace HVK.Models {
     
    public partial class Hvkuser {

       public Hvkuser() {
           Pets = new HashSet<Pet>();
       }
     

        public int HvkuserId { get; set; }
        [Required(ErrorMessage = "First Name is required")]
        [RegularExpression(@"^[a-zA-Z']+$", ErrorMessage = "Only letters and apostrophes are allowed in First Name")]
        [StringLength(25, ErrorMessage = "First Name can only have a max length of 25")]
       [Display(Name = "*First Name")]
       public string FirstName { get; set; }

        [Required(ErrorMessage = "Last Name is required")]
        [RegularExpression(@"^[a-zA-Z']+$", ErrorMessage = "Only letters and apostrophes are allowed in Last Name")]
        [StringLength(25, ErrorMessage = "Last Name can only have a max length of 25")]
       [Display(Name = "*Last Name")]
        public string LastName { get; set; }
        [NotNull]
        [StringLength(50, ErrorMessage = "Email can only have a max length of 50")]
        [EmailAddress(ErrorMessage = "Invalid email address")]
        [Display(Name = "Email Address")]
        public string? Email { get; set; }

       [RegularExpression(@"^\d{10}$", ErrorMessage = "Phone number must be 10 digits")]
       [StringLength(10, ErrorMessage = "Phone number can only have a max length of 10")]
        [Display(Name = "Phone Number")]
        public string? Phone { get; set; }

       [RegularExpression(@"^\d{10}$", ErrorMessage = "Cell Phone number must be 10 digits")]
       [StringLength(10, ErrorMessage = "Cell Phone number can only have a max length of 10")]
        //[OneContact(type)]
       [Display(Name = "Cell Phone Number")]
        public string? CellPhone { get; set; }

        //[Required(ErrorMessage = "Password is required")]
        [NotNull]
        [StringLength(50, ErrorMessage = "Password can only have a max length of 50")]
        public string? Password { get; set; }

        [StringLength(40, ErrorMessage = "Street can only have a max length of 40")]
        public string? Street { get; set; }

        [StringLength(25, ErrorMessage = "Street can only have a max length of 25")]
        public string? City { get; set; }

        public string? Province { get; set; }

        [RegularExpression(@"^[A-Za-z]\d[A-Za-z]\d[A-Za-z]\d$", ErrorMessage = "Invalid postal code format (A1A1A1)")]
        [StringLength(6, ErrorMessage = "Postal code can only have a max length of 6")]
        [Display(Name = "Postal Code")]
        public string? PostalCode { get; set; }

        //[Required(ErrorMessage = "First Name is required")]
        [NotNull]
        [RegularExpression(@"^[a-zA-Z']+$", ErrorMessage = "Only letters and apostrophes are allowed in First Name")]
        [StringLength(25, ErrorMessage = "First Name can only have a max length of 25")]
        [Display(Name = "First Name")]
        public string? EmergencyContactFirstName { get; set; }

        //[Required(ErrorMessage = "Last Name is required")]
        [NotNull]
        [RegularExpression(@"^[a-zA-Z']+$", ErrorMessage = "Only letters and apostrophes are allowed in Last Name")]
        [StringLength(25, ErrorMessage = "Last Name can only have a max length of 25")]
        [Display(Name = "Last Name")]
        public string? EmergencyContactLastName { get; set; }

        //[Required(ErrorMessage = "Phone number is required")]
        [NotNull]
        [RegularExpression(@"^\d{10}$", ErrorMessage = "Phone number must be 10 digits")]
       [StringLength(10, ErrorMessage = "Phone number can only have a max length of 10")]
        [Display(Name = "Phone Number")]
        public string? EmergencyContactPhone { get; set; }

        public string UserType { get; set; } = null!;

        public virtual ICollection<Pet> Pets { get; set; }
    }
}
