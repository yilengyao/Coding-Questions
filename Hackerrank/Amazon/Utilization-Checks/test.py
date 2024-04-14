import solution
import unittest

class CustomTestResult(unittest.TestResult):
    def addFailure(self, test, err):
        super().addFailure(test, err)
        exctype, value, tb = err
        description = test.shortDescription() or str(test)
        # Parse the error message to extract and format "expected" and "actual"
        message = str(value)
        parts = message.split(' != ')
        if len(parts) == 2:
            actual, expected = parts
            print(f"Failure: {description} - Actual: {actual.strip()}, Expected: {expected.strip()}")
        else:
            print(f"Failure: {description} - {message}")

    def addError(self, test, err):
        super().addError(test, err)
        description = test.shortDescription() or str(test)
        print(f"Error: {description} - {str(err[1])}")

class CustomTextTestRunner(unittest.TextTestRunner):
    def run(self, test):
        result = CustomTestResult(self.stream, self.descriptions, self.verbosity)
        test(result)
        self.print_summary(result)
        return result

    def print_summary(self, result):
        print("\nNumber of tests passed:", result.testsRun - len(result.failures) - len(result.errors))
        print("Number of tests failed:", len(result.failures))
        print("Number of tests with errors:", len(result.errors))

class TestFinalInstances(unittest.TestCase):
    def test_low_utilization(self):
        """Test when utilization is consistently low."""
        self.assertEqual(solution.finalInstances(10, [10, 15, 20, 24]), 5)

    def test_high_utilization(self):
        """Test when utilization is consistently high."""
        self.assertEqual(solution.finalInstances(2, [70, 75, 65]), 4)

    def test_no_action(self):
        """Test when no scaling action is required."""
        self.assertEqual(solution.finalInstances(3, [30, 40, 50, 35]), 3)

    def test_alternating_utilization(self):
        """Test alternating between high and low utilizations."""
        self.assertEqual(solution.finalInstances(2, [10, 80, 10, 80, 10]), 1)


    def test_edge_case_max_instances(self):
        """Test edge case where instance count is near maximum limit."""
        self.assertEqual(solution.finalInstances(100000000, [95]), 200000000)

    def test_exact_cooldown_calculation(self):
        """Test the correctness of cooldown periods after scaling actions."""
        self.assertEqual(solution.finalInstances(5, [10, 90]), 3)

def run_tests():
    suite = unittest.TestLoader().loadTestsFromTestCase(TestFinalInstances)
    runner = CustomTextTestRunner(verbosity=2)
    runner.run(suite)

if __name__ == "__main__":
    run_tests()
