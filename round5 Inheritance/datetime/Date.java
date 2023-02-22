class Date {
    private int year;
    private int month;
    private int day;
  
    public Date(int year, int month, int day) throws DateException {
      if (month < 1 || month > 12 || day < 1 || day > 31) {
        throw new DateException("Illegal date " + String.format("%02d", day) + "." + String.format("%02d", month) + "." + year);
      }
      else if (month == 2 && day > 29) {
        throw new DateException("Illegal date " + String.format("%02d", day) + "." + String.format("%02d", month) + "." + year);
      }
      else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
        throw new DateException("Illegal date " + String.format("%02d", day) + "." + String.format("%02d", month) + "." + year);
      }
      this.year = year;
      this.month = month;
      this.day = day;
    }
  
    public int getYear() {
      return year;
    }
  
    public int getMonth() {
      return month;
    }
  
    public int getDay() {
      return day;
    }
  
    public String toString() {
      return String.format("%02d", day) + "." + String.format("%02d", month) + "." + year;
    }
  }