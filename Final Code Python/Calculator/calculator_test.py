import unittest
from appium import webdriver

class calculator_test(unittest.TestCase):
    calcsession = None;
    calresult = None;

    def setUp(self):
        print("setup")
        desired_caps = {}
        desired_caps["app"] = "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App"
        self.calcsession = webdriver.Remote(
            command_executor="http://127.0.0.1:4723",
            desired_capabilities=desired_caps
        )

    def tearDown(self):
        print("teardown")
        self.calcsession.quit()

    def test_add(self):
        print("add")
        self.calcsession.find_element_by_name("One").click()
        self.calcsession.find_element_by_name("Two").click()
        self.calcsession.find_element_by_name("Plus").click()
        self.calcsession.find_element_by_name("Nine").click()
        self.calcsession.find_element_by_name("Equals").click()
        self.assertEqual(self.getDisplayResults(), "21")

    def test_subtraction(self):
        print("subtraction")

    def test_division(self):
        print("division")

    def test_multiplication(self):
        print("multiplication")

    def getDisplayResults(self):
        text = self.calcsession.find_element_by_accessibility_id("CalculatorResults").text
        text = text.strip("Display is ").rstrip(' ').lstrip(' ')
        return text

    def ChooseCalculator(self, locator):
        self.calcsession.find_element_by_accessibility_id("TogglePaneButton").click()
        listElement = self.calcsession.find_elements_by_class_name("ListViewItem")
        for l in listElement:
            if l.get_attribute("AutomationId") == locator:
                l.click()
                break

    def test_choosecaculator(self):
        self.ChooseCalculator("Scientific")

    def ChooseCalculatorXpath(self, locator):
        self.calcsession.find_element_by_accessibility_id("TogglePaneButton").click()
        listElement = self.calcsession.find_elements_by_xpath("//ListItem")
        for l in listElement:
            if l.get_attribute("AutomationId") == locator:
                l.click()
                break

    def test_choosecaculatorxpath(self):
        self.ChooseCalculatorXpath("Scientific")