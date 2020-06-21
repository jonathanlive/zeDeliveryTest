package support.util;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import support.core.DriverManager;
import support.core.PropertieManager;
import support.mobileActions.Actions;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ExtentReportUtil {

    private static ExtentReportUtil _instance;
    private static ExtentReports _extent = new ExtentReports();
    private static boolean isReportCreated;

    private ExtentReportUtil() {

    }

    public static ExtentReportUtil getInstance() {
        if (_instance == null)
            _instance = new ExtentReportUtil();

        return _instance;
    }


    public void createHtmlReporter(String pathname, String reportName, AnalysisStrategy analysisStrategy) {

        if (!isReportCreated) {
            System.out.println("--------- \n Realizando as configurações do relatório. \n --------- ");

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(pathname);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setDocumentTitle(reportName);
            htmlReporter.config().setReportName(reportName);
            htmlReporter.setAnalysisStrategy(analysisStrategy);
            _extent.attachReporter(htmlReporter);
            try {
                _extent.setGherkinDialect("pt");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            _extent.setSystemInfo("productName", PropertieManager.getInstance().readProperties().getProperty("productName"));
            _extent.setSystemInfo("productVersion", PropertieManager.getInstance().readProperties().getProperty("productVersion"));
            _extent.setSystemInfo("platform", PropertieManager.getInstance().readProperties().getProperty("platformName"));
            _extent.setSystemInfo("platformVersion", PropertieManager.getInstance().readProperties().getProperty("platformVersion"));

            isReportCreated = true;
            System.out.println("Relatório configurado com sucesso!");
        }
    }


    public ExtentTest createTest(String testName) {
        return _extent.createTest(testName);
    }

    public void captureImage(String filepath) {

        try {
            Actions.waitForSeconds(1);
            File file = DriverManager.getInstance().getDriver().getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attachFileToReport(ExtentTest test, String filepath) {
        try {
            test.addScreenCaptureFromPath(filepath, "Evidência");
        } catch (IOException e) {
            System.out.println("Imagem não localizada");
        }
    }

    public void flush() {
        System.out.println("Gravando as informações no relatório.");
        _extent.flush();
    }
}
