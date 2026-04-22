package controller.admin;

import java.io.IOException;
import java.util.List;

import dao.BienDao;
import dao.impl.BienDaoImpl;
import model.Bien;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.File;

@WebServlet("/admin/biens")
@MultipartConfig(fileSizeThreshold=1024*1024*2, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
public class AdminBienServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BienDao bienDao = new BienDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "edit":
                    showForm(request, response);
                    break;
                case "delete":
                    deleteBien(request, response);
                    break;
                default:
                    listBiens(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            saveBien(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listBiens(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Bien> biens = bienDao.findAll(); // ✔️ FIX
        request.setAttribute("biens", biens);

        request.getRequestDispatcher("/WEB-INF/views/admin/biens/list.jsp")
               .forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            Bien bien = bienDao.findById(id); // ✔️ FIX
            request.setAttribute("bien", bien);
        }

        request.getRequestDispatcher("/WEB-INF/views/admin/biens/form.jsp") // ✔️ FIX
               .forward(request, response);
    }

    private void saveBien(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String idStr = request.getParameter("id");
        String nom = request.getParameter("nom");
        String capaciteStr = request.getParameter("capacite");
        String localisation = request.getParameter("localisation");
        String equipements = request.getParameter("equipements");
        String description = request.getParameter("description");
        String activeStr = request.getParameter("active");

        String error = null;
        int capacite = 0;

        if (nom == null || nom.trim().isEmpty()) {
            error = "Nom obligatoire";
        } else {
            try {
                capacite = Integer.parseInt(capaciteStr);
                if (capacite <= 0) error = "Capacité invalide";
            } catch (Exception e) {
                error = "Capacité doit être un nombre";
            }
        }

        Bien bien = new Bien();

        if (idStr != null && !idStr.isEmpty()) {
            bien = bienDao.findById(Long.parseLong(idStr));
            if (bien == null) {
                bien = new Bien();
                bien.setId(Long.parseLong(idStr));
            }
        }

        bien.setNom(nom);
        bien.setCapacite(capacite);
        bien.setLocalisation(localisation);
        bien.setEquipements(equipements);
        bien.setDescription(description);
        bien.setActive(activeStr != null);

        // Upload de l'image
        Part part = request.getPart("image");
        if (part != null && part.getSize() > 0) {
            String fileName = java.nio.file.Paths.get(part.getSubmittedFileName()).getFileName().toString();
            // Create path in the deployed webapp folder
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "images" + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            part.write(uploadPath + File.separator + uniqueFileName);
            
            bien.setImageUrl("assets/images/uploads/" + uniqueFileName);
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("bien", bien);

            request.getRequestDispatcher("/WEB-INF/views/admin/biens/form.jsp")
                   .forward(request, response);
            return;
        }

        bienDao.save(bien); // ✔️ FIX

        response.sendRedirect(request.getContextPath() + "/admin/biens"); // ✔️ FIX
    }

    private void deleteBien(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            bienDao.delete(id); // ✔️ FIX
        }

        response.sendRedirect(request.getContextPath() + "/admin/biens");
    }
}