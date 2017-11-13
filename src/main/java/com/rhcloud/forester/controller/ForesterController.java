package com.rhcloud.forester.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rhcloud.forester.core.PdfDocument;
import com.rhcloud.forester.model.Entry;
import com.rhcloud.forester.model.StartUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("request")
public class ForesterController {
	@Autowired
    private PdfDocument pdfDocument;

	@RequestMapping(value = "/start_up/", method = RequestMethod.GET)
	public String handleEnterFirstPoint(ModelMap model) {
		model.addAttribute("page", "start_up");
		return "home";
	}

	@RequestMapping(value = "/first_point/", method = RequestMethod.GET)
	public ModelAndView handleFirstPoint(HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "first_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		model.addAttribute("startPoint", entries.get(0).getStartPoint());
		model.addAttribute("nextPoint", entries.get(0).getNextPoint());
		model.addAttribute("previousPoint", entries.get(0).getPreviousPoint());
		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/first_point/", method = RequestMethod.POST)
	public String handleFirstPoint(HttpServletRequest request, StartUp startUp) {
		List<Entry> entries = new ArrayList<Entry>();
		Entry e = new Entry();
		int i = 0;
		try {
			i = Integer.parseInt(startUp.getStartPoint());
			Integer.parseInt(startUp.getTotalNumber());
		} catch (NumberFormatException exception) {
			return "redirect:/error/";
		}
		String startPoint = Integer.toString(i);
		String nextPoint = Integer.toString(i+1);
		String previousPoint = Integer.toString(i-1);

		e.setStartPoint(startPoint);
		e.setNextPoint(nextPoint);
		e.setPreviousPoint(previousPoint);

		entries.add(e);
		HttpSession session = request.getSession(false);
		session.setAttribute("entries", entries);
		session.setAttribute("totalNumber", startUp.getTotalNumber());
		if (Integer.parseInt(startUp.getTotalNumber())>8 || Integer.parseInt(startUp.getTotalNumber())<1) {
			return "redirect:/error/";
		} else {
			return "redirect:/first_point/";
		}
	}

	@RequestMapping(value = "/second_point/", method = RequestMethod.GET)
	public ModelAndView handleSecondPoint(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "second_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		int previousPointAngel = Integer.parseInt(entries.get(0).getNextPointAngle()) + 180;
		entry.setPreviousPointAngle(Integer.toString(previousPointAngel));

		model.addAttribute("startPoint", entries.get(1).getStartPoint());
		model.addAttribute("nextPoint", entries.get(1).getNextPoint());
		model.addAttribute("previousPoint", entries.get(1).getPreviousPoint());

		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/second_point/", method = RequestMethod.POST)
	public String handleSecondPoint(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");
		Entry nextEntry = new Entry();

		nextEntry.setPreviousPoint(entries.get(0).getStartPoint());
		nextEntry.setStartPoint(entries.get(0).getNextPoint());
		int i = Integer.parseInt(entries.get(0).getNextPoint());
		String nextPoint = Integer.toString(i+1);
		nextEntry.setNextPoint(nextPoint);

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(0).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(0).setNextPointAngle(entry.getNextPointAngle());
		entries.get(0).setPoint1Angle(entry.getPoint1Angle());
		entries.get(0).setPoint2Angle(entry.getPoint2Angle());
		entries.get(0).setPoint3Angle(entry.getPoint3Angle());
		entries.get(0).setPoint1Length(entry.getPoint1Length());
		entries.get(0).setPoint2Length(entry.getPoint2Length());
		entries.get(0).setPoint3Length(entry.getPoint3Length());


		if (Integer.parseInt(totalNumber) == 1) {
			return "redirect:/end_up/";
		} else {
			entries.add(nextEntry);
			session.setAttribute("entries", entries);
			return "redirect:/second_point/";
		}
	}

	@RequestMapping(value = "/third_point/", method = RequestMethod.GET)
	public ModelAndView handleThirdPoint(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "third_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		int previousPointAngel = Integer.parseInt(entries.get(1).getNextPointAngle()) + 180;
		entry.setPreviousPointAngle(Integer.toString(previousPointAngel));

		model.addAttribute("startPoint", entries.get(2).getStartPoint());
		model.addAttribute("nextPoint", entries.get(2).getNextPoint());
		model.addAttribute("previousPoint", entries.get(2).getPreviousPoint());
		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/third_point/", method = RequestMethod.POST)
	public String handleThirdPoint(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");
		Entry nextEntry = new Entry();

		nextEntry.setPreviousPoint(entries.get(1).getStartPoint());
		nextEntry.setStartPoint(entries.get(1).getNextPoint());
		int i = Integer.parseInt(entries.get(1).getNextPoint());
		String nextPoint = Integer.toString(i+1);
		nextEntry.setNextPoint(nextPoint);

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(1).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(1).setNextPointAngle(entry.getNextPointAngle());
		entries.get(1).setPoint1Angle(entry.getPoint1Angle());
		entries.get(1).setPoint2Angle(entry.getPoint2Angle());
		entries.get(1).setPoint3Angle(entry.getPoint3Angle());
		entries.get(1).setPoint1Length(entry.getPoint1Length());
		entries.get(1).setPoint2Length(entry.getPoint2Length());
		entries.get(1).setPoint3Length(entry.getPoint3Length());

		if (Integer.parseInt(totalNumber) == 2) {
			return "redirect:/end_up/";
		} else {
			entries.add(nextEntry);
			session.setAttribute("entries", entries);
			return "redirect:/third_point/";
		}
	}

	@RequestMapping(value = "/fourth_point/", method = RequestMethod.GET)
	public ModelAndView handleFourthPoint(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "fourth_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		int previousPointAngel = Integer.parseInt(entries.get(2).getNextPointAngle()) + 180;
		entry.setPreviousPointAngle(Integer.toString(previousPointAngel));

		model.addAttribute("startPoint", entries.get(3).getStartPoint());
		model.addAttribute("nextPoint", entries.get(3).getNextPoint());
		model.addAttribute("previousPoint", entries.get(3).getPreviousPoint());
		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/fourth_point/", method = RequestMethod.POST)
	public String handleFourthpoint(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");
		Entry nextEntry = new Entry();

		nextEntry.setPreviousPoint(entries.get(2).getStartPoint());
		nextEntry.setStartPoint(entries.get(2).getNextPoint());
		int i = Integer.parseInt(entries.get(2).getNextPoint());
		String nextPoint = Integer.toString(i+1);
		nextEntry.setNextPoint(nextPoint);

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(2).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(2).setNextPointAngle(entry.getNextPointAngle());
		entries.get(2).setPoint1Angle(entry.getPoint1Angle());
		entries.get(2).setPoint2Angle(entry.getPoint2Angle());
		entries.get(2).setPoint3Angle(entry.getPoint3Angle());
		entries.get(2).setPoint1Length(entry.getPoint1Length());
		entries.get(2).setPoint2Length(entry.getPoint2Length());
		entries.get(2).setPoint3Length(entry.getPoint3Length());

		if (Integer.parseInt(totalNumber) == 3) {
			return "redirect:/end_up/";
		} else {
			entries.add(nextEntry);
			session.setAttribute("entries", entries);
			return "redirect:/fourth_point/";
		}
	}

	@RequestMapping(value = "/fifth_point/", method = RequestMethod.GET)
	public ModelAndView handleFifthPoint(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "fifth_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		int previousPointAngel = Integer.parseInt(entries.get(3).getNextPointAngle()) + 180;
		entry.setPreviousPointAngle(Integer.toString(previousPointAngel));

		model.addAttribute("startPoint", entries.get(4).getStartPoint());
		model.addAttribute("nextPoint", entries.get(4).getNextPoint());
		model.addAttribute("previousPoint", entries.get(4).getPreviousPoint());
		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/fifth_point/", method = RequestMethod.POST)
	public String handleFifthpoint(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");
		Entry nextEntry = new Entry();

		nextEntry.setPreviousPoint(entries.get(3).getStartPoint());
		nextEntry.setStartPoint(entries.get(3).getNextPoint());
		int i = Integer.parseInt(entries.get(3).getNextPoint());
		String nextPoint = Integer.toString(i+1);
		nextEntry.setNextPoint(nextPoint);

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(3).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(3).setNextPointAngle(entry.getNextPointAngle());
		entries.get(3).setPoint1Angle(entry.getPoint1Angle());
		entries.get(3).setPoint2Angle(entry.getPoint2Angle());
		entries.get(3).setPoint3Angle(entry.getPoint3Angle());
		entries.get(3).setPoint1Length(entry.getPoint1Length());
		entries.get(3).setPoint2Length(entry.getPoint2Length());
		entries.get(3).setPoint3Length(entry.getPoint3Length());

		if (Integer.parseInt(totalNumber) == 4) {
			return "redirect:/end_up/";
		} else {
			entries.add(nextEntry);
			session.setAttribute("entries", entries);
			return "redirect:/fifth_point/";
		}
	}

	@RequestMapping(value = "/sixth_point/", method = RequestMethod.GET)
	public ModelAndView handleSixthPoint(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "sixth_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		int previousPointAngel = Integer.parseInt(entries.get(4).getNextPointAngle()) + 180;
		entry.setPreviousPointAngle(Integer.toString(previousPointAngel));

		model.addAttribute("startPoint", entries.get(5).getStartPoint());
		model.addAttribute("nextPoint", entries.get(5).getNextPoint());
		model.addAttribute("previousPoint", entries.get(5).getPreviousPoint());
		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/sixth_point/", method = RequestMethod.POST)
	public String handleSixthpoint(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");
		Entry nextEntry = new Entry();

		nextEntry.setPreviousPoint(entries.get(4).getStartPoint());
		nextEntry.setStartPoint(entries.get(4).getNextPoint());
		int i = Integer.parseInt(entries.get(4).getNextPoint());
		String nextPoint = Integer.toString(i+1);
		nextEntry.setNextPoint(nextPoint);

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(4).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(4).setNextPointAngle(entry.getNextPointAngle());
		entries.get(4).setPoint1Angle(entry.getPoint1Angle());
		entries.get(4).setPoint2Angle(entry.getPoint2Angle());
		entries.get(4).setPoint3Angle(entry.getPoint3Angle());
		entries.get(4).setPoint1Length(entry.getPoint1Length());
		entries.get(4).setPoint2Length(entry.getPoint2Length());
		entries.get(4).setPoint3Length(entry.getPoint3Length());

		if (Integer.parseInt(totalNumber) == 5) {
			return "redirect:/end_up/";
		} else {
			entries.add(nextEntry);
			session.setAttribute("entries", entries);
			return "redirect:/sixth_point/";
		}
	}

	@RequestMapping(value = "/seventh_point/", method = RequestMethod.GET)
	public ModelAndView handleSeventhPoint(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "seventh_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		int previousPointAngel = Integer.parseInt(entries.get(5).getNextPointAngle()) + 180;
		entry.setPreviousPointAngle(Integer.toString(previousPointAngel));

		model.addAttribute("startPoint", entries.get(6).getStartPoint());
		model.addAttribute("nextPoint", entries.get(6).getNextPoint());
		model.addAttribute("previousPoint", entries.get(6).getPreviousPoint());
		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/seventh_point/", method = RequestMethod.POST)
	public String handleSeventhpoint(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");
		Entry nextEntry = new Entry();

		nextEntry.setPreviousPoint(entries.get(5).getStartPoint());
		nextEntry.setStartPoint(entries.get(5).getNextPoint());
		int i = Integer.parseInt(entries.get(5).getNextPoint());
		String nextPoint = Integer.toString(i+1);
		nextEntry.setNextPoint(nextPoint);

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(5).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(5).setNextPointAngle(entry.getNextPointAngle());
		entries.get(5).setPoint1Angle(entry.getPoint1Angle());
		entries.get(5).setPoint2Angle(entry.getPoint2Angle());
		entries.get(5).setPoint3Angle(entry.getPoint3Angle());
		entries.get(5).setPoint1Length(entry.getPoint1Length());
		entries.get(5).setPoint2Length(entry.getPoint2Length());
		entries.get(5).setPoint3Length(entry.getPoint3Length());

		if (Integer.parseInt(totalNumber) == 6) {
			return "redirect:/end_up/";
		} else {
			entries.add(nextEntry);
			session.setAttribute("entries", entries);
			return "redirect:/seventh_point/";
		}
	}

	@RequestMapping(value = "/eight_point/", method = RequestMethod.GET)
	public ModelAndView handleEightPoint(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "eight_point");
		HttpSession session = request.getSession(false);
		List<Entry> entries = new ArrayList<Entry>();
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		int previousPointAngel = Integer.parseInt(entries.get(6).getNextPointAngle()) + 180;
		entry.setPreviousPointAngle(Integer.toString(previousPointAngel));

		model.addAttribute("startPoint", entries.get(7).getStartPoint());
		model.addAttribute("nextPoint", entries.get(7).getNextPoint());
		model.addAttribute("previousPoint", entries.get(7).getPreviousPoint());
		model.addAttribute("totalNumber", totalNumber);

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/eight_point/", method = RequestMethod.POST)
	public String handleEighthpoint(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");
		Entry nextEntry = new Entry();

		nextEntry.setPreviousPoint(entries.get(6).getStartPoint());
		nextEntry.setStartPoint(entries.get(6).getNextPoint());
		int i = Integer.parseInt(entries.get(6).getNextPoint());
		String nextPoint = Integer.toString(i+1);
		nextEntry.setNextPoint(nextPoint);

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(6).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(6).setNextPointAngle(entry.getNextPointAngle());
		entries.get(6).setPoint1Angle(entry.getPoint1Angle());
		entries.get(6).setPoint2Angle(entry.getPoint2Angle());
		entries.get(6).setPoint3Angle(entry.getPoint3Angle());
		entries.get(6).setPoint1Length(entry.getPoint1Length());
		entries.get(6).setPoint2Length(entry.getPoint2Length());
		entries.get(6).setPoint3Length(entry.getPoint3Length());

		if (Integer.parseInt(totalNumber) == 7) {
			return "redirect:/end_up/";
		} else {
			entries.add(nextEntry);
			session.setAttribute("entries", entries);
			return "redirect:/eight_point/";
		}
	}

	@RequestMapping(value = "/end_up/", method = RequestMethod.GET)
	public ModelAndView handleEndUp(@ModelAttribute Entry entry, HttpServletRequest request, ModelMap model) {
		model.addAttribute("page", "end_up");
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		String totalNumber = (String) session.getAttribute("totalNumber");

		if (Integer.parseInt(totalNumber) == 8) {
			int previousPointAngel = Integer.parseInt(entries.get(6).getNextPointAngle()) + 180;
			entry.setPreviousPointAngle(Integer.toString(previousPointAngel));
		}

		return new ModelAndView("home", "command", new Entry());
	}

	@RequestMapping(value = "/end_up/", method = RequestMethod.POST)
	public String handleEndUp(HttpServletRequest request, Entry entry) {
		List<Entry> entries = new ArrayList<Entry>();

		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");

		try {
			Integer.parseInt(entry.getPreviousPointAngle());
			Integer.parseInt(entry.getNextPointAngle());
			Integer.parseInt(entry.getPoint1Angle());
			Integer.parseInt(entry.getPoint2Angle());
			Integer.parseInt(entry.getPoint3Angle());
		} catch (Exception e) {
			return "redirect:/error/";
		}
		entries.get(7).setPreviousPointAngle(entry.getPreviousPointAngle());
		entries.get(7).setNextPointAngle(entry.getNextPointAngle());
		entries.get(7).setPoint1Angle(entry.getPoint1Angle());
		entries.get(7).setPoint2Angle(entry.getPoint2Angle());
		entries.get(7).setPoint3Angle(entry.getPoint3Angle());
		entries.get(7).setPoint1Length(entry.getPoint1Length());
		entries.get(7).setPoint2Length(entry.getPoint2Length());
		entries.get(7).setPoint3Length(entry.getPoint3Length());

		session.setAttribute("entries", entries);

		return "redirect:/end_up/";
	}

	@RequestMapping(value = "/generate_and_show_pdf_file/", method = RequestMethod.GET)
	public ResponseEntity<byte[]> handleShowPdfFile(HttpServletRequest request) {
		List<Entry> entries = new ArrayList<Entry>();
		HttpSession session = request.getSession(false);
		entries = (List<Entry>) session.getAttribute("entries");
		session.removeAttribute("entries");
		session.removeAttribute("totalNumber");
		try {
			pdfDocument.create(entries);
		} catch (Exception e) {
			System.out.println("Error during generation document");
		}

		String filename = "forester.pdf";
		String filePath = "/tmp/"+filename;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("content-disposition", "inline;filename=" + filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		Path pdfPath = Paths.get(filePath);
		byte[] pdfBytes = null;
		try {
			pdfBytes = Files.readAllBytes(pdfPath);
		} catch (Exception e) {

		}
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/error/", method = RequestMethod.GET)
	public String handleError(ModelMap model) {
		model.addAttribute("page", "error");
		return "home";
	}

}
