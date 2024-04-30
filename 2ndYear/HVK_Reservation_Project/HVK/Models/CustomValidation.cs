using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;

namespace HVK.Models {

    public class CustomValidation {

        public class OneContactAttribute : ValidationAttribute {
            private readonly string _type;

            public OneContactAttribute(string type) {
                _type = type;
            }
            protected override ValidationResult IsValid(object value, ValidationContext validationContext) {
                string email = (string)validationContext.ObjectType.GetProperty("Email").GetValue(validationContext.ObjectInstance);
                string phone = (string)validationContext.ObjectType.GetProperty("Phone").GetValue(validationContext.ObjectInstance);
                string cellPhone = (string)validationContext.ObjectType.GetProperty("CellPhone").GetValue(validationContext.ObjectInstance);


                if (/*_type == "employee" && */(string.IsNullOrEmpty(email) && string.IsNullOrEmpty(phone) && string.IsNullOrEmpty(cellPhone))) {
                    return new ValidationResult("At least one of Email Address, Phone Number or Cell Phone Number must be provided for Employee.");
                }


                if (/*_type == "customer" &&*/ string.IsNullOrEmpty(email)) {
                    return new ValidationResult("Email is mandatory");
                }

                return ValidationResult.Success;
            }
        }
        public class NotNullAttribute : ValidationAttribute {
            protected override ValidationResult IsValid(object value, ValidationContext validationContext) {
                if (value == null) {
                    return new ValidationResult($"{validationContext.DisplayName} is required.");
                }

                return ValidationResult.Success;
            }
        }

        public class PasswordMatchAttribute : ValidationAttribute {
            protected override ValidationResult IsValid(object value, ValidationContext validationContext) {
                var passwordProperty = validationContext.ObjectType.GetProperty("Password");

                if (passwordProperty == null) {
                    throw new ArgumentException("Password property not found.");
                }

                var passwordValue = passwordProperty.GetValue(validationContext.ObjectInstance, null);

                // If both values are null, consider them as matching
                if (value == null && passwordValue == null) {
                    return ValidationResult.Success;
                }

                // If one value is null and the other isn't, consider them as not matching
                if (value == null || passwordValue == null || !value.Equals(passwordValue)) {
                    return new ValidationResult(ErrorMessage);
                }

                return ValidationResult.Success;
            }
        }
        public class MyDateAttribute : ValidationAttribute {
            public override bool IsValid(object value) {
                if (value is DateTime) {
                    DateTime d = Convert.ToDateTime(value);
                    return d >= DateTime.Now;
                }
                return true;
            }
        }

        public class RequiredDateAttribute : ValidationAttribute {
            public override bool IsValid(object value) {
                return value != null && !string.IsNullOrWhiteSpace(value.ToString());
            }
        }

        public class StartDateAttribute : ValidationAttribute {
            public override bool IsValid(object value) {
                if (value is DateTime) {
                    DateTime startDate = Convert.ToDateTime(value);
                    return startDate >= DateTime.Now;
                }
                return true;
            }
        }

        public class EndDateAttribute : ValidationAttribute {
            public string StartDatePropertyName { get; set; }

            protected override ValidationResult IsValid(object value, ValidationContext validationContext) {
                if (value is DateTime) {
                    DateTime endDate = Convert.ToDateTime(value);

                    var propertyInfo = validationContext.ObjectType.GetProperty(StartDatePropertyName);
                    if (propertyInfo == null) {
                        return new ValidationResult($"Unknown property: {StartDatePropertyName}");
                    }

                    var startDateValue = propertyInfo.GetValue(validationContext.ObjectInstance, null);
                    if (startDateValue is DateTime startDate) {
                        if (endDate > startDate) {
                            return ValidationResult.Success;
                        }
                        return new ValidationResult("End date must be after the start date.");
                    }

                    return new ValidationResult($"Invalid type for property: {StartDatePropertyName}");
                }
                return ValidationResult.Success;
            }
        }

    }
}
