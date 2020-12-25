//package utils;
//
//import com.adel.model.Course;
//import com.adel.model.Grade;
//import com.adel.model.Professor;
//import com.adel.model.Student;
//import com.lowagie.text.Document;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfReader;
//import com.lowagie.text.pdf.PdfWriter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.FileOutputStream;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class PdfGenerator {
//
//    private static Logger LOG = LoggerFactory.getLogger(PdfGenerator.class);
//
//    String pdfPath= "/temp/";
//
//    public String generateCoursePdf(Course course, Professor professor, Grade grade, Student student) throws Exception {
//
//        try {
//            Date dateCreation = new Date();
//            String pdfName = "admission_system_course_report" + dateCreation.getTime() + ".pdf";
//
//            Document document = new Document();
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath + pdfName));
//            document.open();
//
//            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//
//            String today = format.format(new Date());
//            String courseStartDate = format.format(course.getProfessor());
//            String courseEndDate = format.format(course.getDescription());
//
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("MCIT Admission System - Course Report"));
//            document.add(new Paragraph("Date: " + today ));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("Course: " + course.getCourseId() + " / " + course.getName()));
//            document.add(new Paragraph("Professor: " + course.getProfessor().getFirstName() + " "
//                    + course.getProfessor().getLastName()));
//            document.add(new Paragraph("Start: " + courseStartDate));
//            document.add(new Paragraph("End: " + courseEndDate));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("Students:"));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph(" "));
//
//            PdfPTable table = new PdfPTable(6);
//
//            PdfPCell cell = null;
//            cell = new PdfPCell(new Phrase("ID"));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("First Name"));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("Last Name"));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("Username"));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("E-Mail"));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("Mark"));
//            table.addCell(cell);
//
//            for (Course courseStudents : course.getDescription()) {
//
//                try {
//
//                    cell = new PdfPCell(new Phrase(courseStudents.getStudent().getId().toString()));
//                    table.addCell(cell);
//                    cell = new PdfPCell(new Phrase(courseStudents.getStudent().getFirstName()));
//                    table.addCell(cell);
//                    cell = new PdfPCell(new Phrase(courseStudents.getStudent().getLastName()));
//                    table.addCell(cell);
////                    cell = new PdfPCell(new Phrase(courseStudents.getStudent().getUser().getUserName()));
//                    table.addCell(cell);
////                    cell = new PdfPCell(new Phrase(courseStudents.getStudent().getUser().getEmail()));
//                    table.addCell(cell);
//                    if (courseStudents.getMark() != null) {
//                        cell = new PdfPCell(new Phrase(courseStudents.getMark().toString()));
//                    } else
//                    {
//                        cell = new PdfPCell(new Phrase("-"));
//                    }
//                    table.addCell(cell);
//
//                } catch (Exception e) {
//                    LOG.error("Error generation course report", e);
//                    throw new Exception("Could not add student to table while generating the report");
//                }
//
//            }
//
//            document.add(table);
//
//            document.close();
//            return pdfPath + pdfName;
//        } catch (Exception e) {
//            LOG.error("Error generating course report", e);
//        }
//        return null;
//    }
//
//    private PdfReader downloadPdf(String url) throws Exception {
//
//        return new PdfReader(new URL(url));
//    }
//
//
//}
