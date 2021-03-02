import unittest
from appium import webdriver
from calc import calc_PO
from selenium.webdriver.common.action_chains import ActionChains

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
        cs = calc_PO.calc_PO(self.calcsession)
        cs.One().click()
        cs.Two().click()
        self.calcsession.find_element_by_name("Plus").click()
        self.calcsession.find_element_by_name("Nine").click()
        cs.Equals().click()
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

    def test_movecalc(self):
        move = ActionChains(self.calcsession)
        move.click_and_hold(self.calcsession.find_element_by_accessibility_id("AppName"))\
            .move_by_offset(50, 50)\
            .perform()
        move.click_and_hold(self.calcsession.find_element_by_accessibility_id("AppName")) \
            .move_by_offset(-50, -50) \
            .perform()
        move.context_click(self.calcsession.find_element_by_accessibility_id("AppName"))
        