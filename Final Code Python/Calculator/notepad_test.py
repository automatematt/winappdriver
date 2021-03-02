import unittest
from appium import webdriver
from notepad import notepad_PO
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys

class notepad_test(unittest.TestCase):
    notepad = None;

    def setUp(self):
        print("setup")
        desired_caps = {}
        desired_caps["app"] = "c:\\windows\\system32\\notepad.exe"
        self.notepad = webdriver.Remote(
            command_executor="http://127.0.0.1:4723",
            desired_capabilities=desired_caps
        )

    def tearDown(self):
        print("teardown")
        self.notepad.quit()

    def test_Notepad(self):
        print("notepad")
        np = notepad_PO.notepad_PO(self.notepad)
        self.notepad.find_element_by_name("Edit").send_keys("windriver is awesome")
        np.textArea().send_keys("windriver is awesome")
        np.textArea().send_keys(Keys.ALT, Keys.F4)
        np.dialogCancel().click()
        np.textArea().send_keys(Keys.ALT, Keys.F4)
        np.dialogDontSave().click()