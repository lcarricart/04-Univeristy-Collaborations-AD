/*******************************************************************************************************************
 * Objective of the class: Defines modern color scheme and styling constants for the entire application.
 *******************************************************************************************************************
 * Context: This is part of a major programming project, where live telemetry is transmitted to a PC, to be later
   manipulated and displayed with a Java GUI application.
 *******************************************************************************************************************
 * Authors: 
 * 	- Luciano Carricart, https://github.com/lcarricart/
 * 	- Georgii Molyboga, https://github.com/Georgemolyboga/
 * Status: Information Engineering students, HAW Hamburg, Germany.
 * Date: November 2024
 *******************************************************************************************************************
 * Public constants:
 * 	- Color constants for modern dark theme
 * 	- Font constants for improved typography
 * 	- Spacing and sizing constants
 *******************************************************************************************************************/

package gui;

import java.awt.Color;
import java.awt.Font;

public class ModernTheme {
	
	// Background Colors - Dark Theme
	public static final Color BACKGROUND_DARK = new Color(26, 32, 44);          // Main canvas background
	public static final Color PANEL_DARK = new Color(45, 55, 72);               // Side panel background
	public static final Color CONTROL_BG = new Color(54, 66, 87);               // Control backgrounds
	
	// Accent Colors
	public static final Color ACCENT_BLUE = new Color(66, 153, 225);            // Primary accent
	public static final Color ACCENT_CYAN = new Color(56, 178, 172);            // Secondary accent
	public static final Color ACCENT_PURPLE = new Color(159, 122, 234);         // Tertiary accent
	
	// Text Colors
	public static final Color TEXT_PRIMARY = new Color(237, 242, 247);          // Main text
	public static final Color TEXT_SECONDARY = new Color(160, 174, 192);        // Secondary text
	public static final Color TEXT_MUTED = new Color(113, 128, 150);            // Muted text
	
	// Grid and Axis Colors
	public static final Color GRID_MAJOR = new Color(74, 85, 104, 180);         // Major grid lines
	public static final Color GRID_MINOR = new Color(74, 85, 104, 80);          // Minor grid lines
	public static final Color AXIS_COLOR = new Color(160, 174, 192);            // Axes
	
	// Data Plot Colors - Vibrant and Modern
	public static final Color PLOT_1 = new Color(56, 189, 248);                 // Cyan-Blue
	public static final Color PLOT_2 = new Color(251, 146, 60);                 // Orange
	public static final Color PLOT_3 = new Color(134, 239, 172);                // Green
	public static final Color PLOT_SORTED = new Color(217, 70, 239);            // Magenta
	
	// Analysis Feature Colors
	public static final Color ZEROS_COLOR = new Color(251, 191, 36);            // Amber for zeros
	public static final Color EXTREMA_MAX = new Color(239, 68, 68);             // Red for maxima
	public static final Color EXTREMA_MIN = new Color(59, 130, 246);            // Blue for minima
	
	// Border and Divider Colors
	public static final Color BORDER_COLOR = new Color(74, 85, 104);            // Borders
	public static final Color DIVIDER_COLOR = new Color(54, 66, 87);            // Dividers
	
	// Fonts
	public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
	public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
	public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 12);
	public static final Font AXIS_LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 11);
	public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 10);
	
	// Spacing Constants
	public static final int PADDING_SMALL = 8;
	public static final int PADDING_MEDIUM = 12;
	public static final int PADDING_LARGE = 20;
	public static final int BORDER_RADIUS = 6;
}
