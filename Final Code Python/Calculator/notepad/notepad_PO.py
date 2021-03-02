class notepad_PO():
    def __init__(self, wd):
        self.driver = wd

    def textArea(self):
        return self.driver.find_element_by_class_name("Edit")

    def dialogDontSave(self):
        return self.driver.find_element_by_name("Don't Save")

    def dialogCancel(self):
        return self.driver.find_element_by_name("Cancel")