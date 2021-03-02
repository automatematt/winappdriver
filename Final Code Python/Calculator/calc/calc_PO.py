class calc_PO():
    def __init__(self, wd):
        self.driver = wd

    def One(self):
        return self.driver.find_element_by_name("One")

    def Two(self):
        return self.driver.find_element_by_name("Two")

    def Equals(self):
        return self.driver.find_element_by_name("Equals")