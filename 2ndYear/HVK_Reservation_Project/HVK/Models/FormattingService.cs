namespace HVK.Models {
    public class FormattingService {
        public string DateDisplay(DateTime? d) {
            string dx = $"{d:yyyy\\/MM\\/dd}";
            return dx;
        }
        public string PhoneDisplay(string? tel) {
            if (tel != null) {
                string phone = "(" + tel.Substring(0, 3) + ") " + tel.Substring(3, 3) + "-" + tel.Substring(6, 3);
                return phone;
            }
            return "N/A";
        }
        public string PostalDisplay(string? pc) {
            if (pc == null)
                return "N/A";
            return pc.Substring(0, 3) + " " + pc.Substring(3);
        }
        public string SizeDisplay(string? s) {
           s = s.Trim().ToLower();
        if (s == "s") {
                return "Small";
            }
        if (s == "m") {
                return "Medium";
            }
        if (s == "l") {
                return "Large";
                    }
            return "";
        }
    }
}
