/*
============================================
JAVA SWING COMPLETE GUIDE - PART 1 of 2
FROM BASICS TO ADVANCED
============================================

THIS FILE CONTAINS:
1. JFrame & JPanel Fundamentals
2. FlowLayout 
3. BorderLayout
4. GridLayout  
5. BoxLayout

PART 2 (separate file) will contain:
- CardLayout
- GridBagLayout
- Null Layout
- Comparison Table
- MEGA Combined Example

============================================
SECTION 1: JFRAME & JPANEL FUNDAMENTALS
============================================
*/

import javax.swing.*;
import java.awt.*;

/*
WHAT IS JFRAME?
---------------
Real-world analogy: JFrame is like the physical window frame of your house.
It's the main container that holds everything. It has:
- Title bar (top with minimize, maximize, close buttons)
- Border (edges of the window)
- Content area (where you put your stuff)

WHAT IS JPANEL?
---------------
Real-world analogy: JPanel is like a canvas or board you place inside your window frame.
- You can paint on it (set background color)
- You can organize things on it (add buttons, text fields, etc.)
- You can have multiple panels in one frame (like having multiple boards)

DIFFERENCE:
- JFrame = The window itself (only ONE per window)
- JPanel = Container inside the window (can have MANY)
*/

// ============================================
// EXAMPLE 1: Simple Empty Window (200x200)
// ============================================
class Example1_SimpleWindow {
    public static void main(String[] args) {
        // Create a new window (frame)
        JFrame frame = new JFrame();
        
        // Set the size: width=200px, height=200px
        frame.setSize(200, 200);
        
        // What happens when user clicks X button?
        // EXIT_ON_CLOSE = closes window AND exits program
        // Other options: HIDE_ON_CLOSE, DISPOSE_ON_CLOSE, DO_NOTHING_ON_CLOSE
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set window title (text at top)
        frame.setTitle("My First Window");
        
        // Center the window on screen
        // null = center relative to screen
        // If you pass another component, it centers relative to that
        frame.setLocationRelativeTo(null);
        
        // Make window visible
        // WITHOUT this line, window is created but INVISIBLE!
        // Always call this LAST after setting up everything
        frame.setVisible(true);
        
        // OUTPUT: A small gray window appears in center of screen
    }
}

// ============================================
// EXAMPLE 2: Window with Red Colored Panel
// ============================================
class Example2_RedPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Red Panel Window");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a panel (canvas/board)
        JPanel panel = new JPanel();
        
        // Set background color to RED
        panel.setBackground(Color.RED);
        
        // Add panel to frame
        // By default, it fills the entire content area
        frame.add(panel);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Window with completely red interior
    }
}

// ============================================
// EXAMPLE 3: Window with 2 Different Colored Panels
// ============================================
class Example3_TwoPanels {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Two Panels");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create first panel
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setPreferredSize(new Dimension(200, 300));
        
        // Create second panel
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.GREEN);
        panel2.setPreferredSize(new Dimension(200, 300));
        
        // Add both panels
        // They will be placed side by side (default FlowLayout)
        frame.add(panel1);
        frame.add(panel2);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Blue panel on left, green panel on right
    }
}

/*
COMMON BEGINNER ERRORS & FIXES:
--------------------------------
1. ERROR: Window appears but is invisible
   FIX: Call setVisible(true) - you forgot this!

2. ERROR: Window shows but content is missing
   FIX: Call setVisible(true) AFTER adding all components

3. ERROR: Program doesn't exit when closing window
   FIX: Use setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

4. ERROR: Window is tiny (appears as just title bar)
   FIX: Call setSize() or pack() before setVisible()

5. ERROR: Nothing happens when running code
   FIX: Make sure you have main() method and it's public static void

============================================
PART A: FLOWLAYOUT
============================================

WHAT IS FLOWLAYOUT?
-------------------
Real-world example: Like words in a book or paragraph.
- Components flow left to right
- When a row is full, it wraps to next row
- Like typing: text flows until line is full, then goes to next line

HOW IT WORKS:
-------------
┌─────────────────────────┐
│ [Btn1] [Btn2] [Btn3]   │  ← First row fills left to right
│ [Btn4] [Btn5]          │  ← Wraps to next row when space runs out
└─────────────────────────┘

CONSTRUCTORS:
-------------
1. FlowLayout()                    → Center aligned, 5px gaps
2. FlowLayout(int align)           → Custom alignment, 5px gaps
3. FlowLayout(align, hgap, vgap)   → Custom alignment and gaps

ALIGNMENT OPTIONS:
------------------
- FlowLayout.LEFT    → All components stick to left
- FlowLayout.CENTER  → Center of each row (DEFAULT)
- FlowLayout.RIGHT   → All components stick to right

GAPS:
-----
- hgap = Horizontal gap (space between components left-right)
- vgap = Vertical gap (space between rows top-bottom)

WHAT HAPPENS ON RESIZE?
------------------------
When you make window wider:  More components fit per row
When you make window smaller: Components wrap to more rows
*/

// ============================================
// FLOWLAYOUT EXAMPLE 1: Default Center Alignment
// ============================================
class FlowExample1_DefaultCenter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FlowLayout - Default Center");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create panel with default FlowLayout
        // FlowLayout is DEFAULT for JPanel, but we'll be explicit
        JPanel panel = new JPanel(new FlowLayout());
        
        // Add 5 buttons
        panel.add(new JButton("Button 1"));
        panel.add(new JButton("Button 2"));
        panel.add(new JButton("Button 3"));
        panel.add(new JButton("Button 4"));
        panel.add(new JButton("Button 5"));
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Buttons centered, flowing left to right
        // Try resizing window - buttons will reflow!
    }
}

// ============================================
// FLOWLAYOUT EXAMPLE 2: Left Aligned with Custom Gaps
// ============================================
class FlowExample2_LeftAlignedGaps {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FlowLayout - Left Aligned");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Left aligned with 20px horizontal gap, 10px vertical gap
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        
        // Add buttons
        for (int i = 1; i <= 8; i++) {
            panel.add(new JButton("Button " + i));
        }
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Buttons aligned to left with 20px space between them
        // 10px vertical space between rows
    }
}

// ============================================
// FLOWLAYOUT EXAMPLE 3: Right Aligned Login Form
// ============================================
class FlowExample3_RightAligned {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FlowLayout - Right Aligned");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
        
        panel.add(new JLabel("Username:"));
        panel.add(new JTextField(15));
        panel.add(new JLabel("Password:"));
        panel.add(new JPasswordField(15));
        panel.add(new JButton("Login"));
        panel.add(new JButton("Cancel"));
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: All components aligned to right side
        // Flows naturally like text
    }
}

/*
============================================
PART B: BORDERLAYOUT
============================================

WHAT IS BORDERLAYOUT?
---------------------
Real-world example: Think of a house layout:
- NORTH = Roof/attic
- SOUTH = Basement/ground floor
- EAST = Right wing
- WEST = Left wing  
- CENTER = Main living area

VISUAL REPRESENTATION:
----------------------
┌─────────────────────────┐
│       NORTH             │ ← Top strip (full width)
├────┬─────────────┬──────┤
│    │             │      │
│WEST│   CENTER    │ EAST │ ← Middle section
│    │   (Main)    │      │
├────┴─────────────┴──────┤
│       SOUTH             │ ← Bottom strip (full width)
└─────────────────────────┘

KEY FEATURES:
-------------
1. Only 5 regions: NORTH, SOUTH, EAST, WEST, CENTER
2. Each region can hold ONE component
3. BorderLayout is DEFAULT for JFrame
4. If you don't specify region, it goes to CENTER

SIZING BEHAVIOR:
----------------
- NORTH/SOUTH: Full width, preferred height
- EAST/WEST: Full height, preferred width
- CENTER: Takes all remaining space

WHAT IF WE ADD MULTIPLE TO ONE REGION?
---------------------------------------
The LAST component added replaces the previous one!
Only the last component will be visible.

HOW COMPONENTS RESIZE:
----------------------
- NORTH/SOUTH stretch horizontally
- EAST/WEST stretch vertically
- CENTER stretches in BOTH directions (fills remaining space)
*/

// ============================================
// BORDERLAYOUT EXAMPLE 1: All 5 Regions with Buttons
// ============================================
class BorderExample1_AllRegions {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BorderLayout - All Regions");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // JFrame has BorderLayout by DEFAULT
        // But let's be explicit for learning
        frame.setLayout(new BorderLayout());
        
        // Add button to each region
        frame.add(new JButton("NORTH"), BorderLayout.NORTH);
        frame.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        frame.add(new JButton("EAST"), BorderLayout.EAST);
        frame.add(new JButton("WEST"), BorderLayout.WEST);
        frame.add(new JButton("CENTER"), BorderLayout.CENTER);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: 5 buttons in cross pattern
        // Try resizing - CENTER grows, edges stretch appropriately
    }
}

// ============================================
// BORDERLAYOUT EXAMPLE 2: Typical Layout
// ============================================
class BorderExample2_TypicalLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BorderLayout - Typical Usage");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create toolbar panel for top
        JPanel toolbar = new JPanel();
        toolbar.setBackground(Color.LIGHT_GRAY);
        toolbar.add(new JButton("New"));
        toolbar.add(new JButton("Open"));
        toolbar.add(new JButton("Save"));
        
        // Create main content panel
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.add(new JLabel("Main Content Area"));
        
        // Add to frame
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(content, BorderLayout.CENTER);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Toolbar at top, main content fills rest
        // This is VERY common in applications!
    }
}

// ============================================
// BORDERLAYOUT EXAMPLE 3: Simple Text Editor Layout
// ============================================
class BorderExample3_TextEditor {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Text Editor");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Toolbar at top
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(new JButton("Bold"));
        toolbar.add(new JButton("Italic"));
        toolbar.add(new JButton("Underline"));
        toolbar.add(new JButton("Font"));
        
        // Text area in center
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Status bar at bottom
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.add(new JLabel("Line 1, Column 1"));
        
        // Add all components
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(statusBar, BorderLayout.SOUTH);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Professional text editor layout
        // Toolbar top, text area center, status bottom
    }
}

// ============================================
// BORDERLAYOUT EXAMPLE 4: Calculator Layout
// ============================================
class BorderExample4_Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Display at top
        JTextField display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        // Button panel in center
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        for (String text : buttons) {
            buttonPanel.add(new JButton(text));
        }
        
        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Calculator with display on top, buttons below
    }
}

/*
============================================
PART C: GRIDLAYOUT
============================================

WHAT IS GRIDLAYOUT?
-------------------
Simple explanation: Like a chess board or Excel spreadsheet.
- Divides space into equal-sized cells
- Cells arranged in rows and columns
- Each cell is EXACTLY the same size
- Components are added left to right, top to bottom

VISUAL:
-------
┌────┬────┬────┐
│ 1  │ 2  │ 3  │  ← First row fills left to right
├────┼────┼────┤
│ 4  │ 5  │ 6  │  ← Then second row
├────┼────┼────┤
│ 7  │ 8  │ 9  │  ← Then third row
└────┴────┴────┘

CONSTRUCTORS:
-------------
1. GridLayout()                      → 1 row, any number of columns
2. GridLayout(int rows, int cols)    → Specify rows and columns
3. GridLayout(rows, cols, hgap, vgap) → With gaps between cells

KEY RULES:
----------
1. At least ONE of rows/cols must be non-zero
2. If rows=0, columns are fixed, rows grow as needed
3. If cols=0, rows are fixed, columns grow as needed
4. All cells are EQUAL size (no exceptions!)

MORE COMPONENTS THAN CELLS?
----------------------------
Extra rows are added automatically

FEWER COMPONENTS?
-----------------
Remaining cells stay empty

HOW COMPONENTS RESIZE?
-----------------------
When window resizes, ALL cells resize equally.
Every component gets same space - fair share!
*/

// ============================================
// GRIDLAYOUT EXAMPLE 1: 2x2 Grid with 4 Buttons
// ============================================
class GridExample1_Simple2x2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridLayout - 2x2");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create 2 rows, 2 columns
        JPanel panel = new JPanel(new GridLayout(2, 2));
        
        // Add 4 buttons - fill order: left to right, top to bottom
        panel.add(new JButton("Button 1"));
        panel.add(new JButton("Button 2"));
        panel.add(new JButton("Button 3"));
        panel.add(new JButton("Button 4"));
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: 4 equal-sized buttons in 2x2 grid
        // Resize window - all buttons grow/shrink equally
    }
}

// ============================================
// GRIDLAYOUT EXAMPLE 2: 3x3 Calculator Buttons
// ============================================
class GridExample2_Calculator3x3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator Buttons 3x3");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 3 rows, 3 columns with 5px gaps
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        panel.setBackground(Color.BLACK); // Gaps will show black
        
        // Add buttons 1-9
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(btn);
        }
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Numbers 1-9 in 3x3 grid with spacing
    }
}

// ============================================
// GRIDLAYOUT EXAMPLE 3: 4x2 Form Grid
// ============================================
class GridExample3_FormGrid {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration Form");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 4 rows, 2 columns: labels on left, fields on right
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        // Row 1: Name
        panel.add(new JLabel("Name:"));
        panel.add(new JTextField());
        
        // Row 2: Email
        panel.add(new JLabel("Email:"));
        panel.add(new JTextField());
        
        // Row 3: Phone
        panel.add(new JLabel("Phone:"));
        panel.add(new JTextField());
        
        // Row 4: Buttons
        panel.add(new JButton("Submit"));
        panel.add(new JButton("Cancel"));
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Clean form with labels left, fields right
        // All rows have equal height
    }
}

// ============================================
// GRIDLAYOUT EXAMPLE 4: Complete Calculator
// ============================================
class GridExample4_FullCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator - GridLayout");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main layout
        frame.setLayout(new BorderLayout(5, 5));
        
        // Display field at top
        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        // Button panel with 4x4 grid
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBackground(Color.DARK_GRAY);
        
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
        
        for (String label : buttonLabels) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            buttonPanel.add(btn);
        }
        
        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Professional calculator layout
        // Equal-sized buttons in perfect grid
    }
}

/*
============================================
PART D: BOXLAYOUT
============================================

WHAT IS BOXLAYOUT?
------------------
Different from FlowLayout: BoxLayout arranges components in SINGLE LINE
- Either VERTICAL (one column, stacked top to bottom)
- Or HORIZONTAL (one row, side by side left to right)

DIFFERENCE FROM FLOWLAYOUT:
---------------------------
FlowLayout: Wraps to next line when space runs out
BoxLayout: NEVER wraps, stays in single line

TWO ORIENTATIONS:
-----------------
1. BoxLayout.Y_AXIS → Vertical (top to bottom)
   ┌─────────┐
   │ Button1 │
   │ Button2 │
   │ Button3 │
   └─────────┘

2. BoxLayout.X_AXIS → Horizontal (left to right)
   ┌──────────────────────────┐
   │ [Btn1] [Btn2] [Btn3]    │
   └──────────────────────────┘

CREATION SYNTAX:
----------------
new BoxLayout(container, BoxLayout.Y_AXIS)  // Vertical
new BoxLayout(container, BoxLayout.X_AXIS)  // Horizontal

SPACING METHODS:
----------------
1. Box.createRigidArea(new Dimension(width, height))
   → Fixed space (like a rigid block)

2. Box.createVerticalStrut(height)
   → Vertical spacing of exact height

3. Box.createHorizontalStrut(width)
   → Horizontal spacing of exact width

4. Box.createGlue()
   → Flexible spacing (pushes components apart)
*/

// ============================================
// BOXLAYOUT EXAMPLE 1: Vertical Stack
// ============================================
class BoxExample1_VerticalStack {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BoxLayout - Vertical");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Add 5 buttons stacked vertically
        for (int i = 1; i <= 5; i++) {
            JButton btn = new JButton("Button " + i);
            panel.add(btn);
            
            // Add spacing between buttons (except after last)
            if (i < 5) {
                panel.add(Box.createVerticalStrut(10));
            }
        }
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Buttons stacked vertically with 10px between
    }
}

// ============================================
// BOXLAYOUT EXAMPLE 2: Horizontal Side-by-Side
// ============================================
class BoxExample2_HorizontalSideBySide {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BoxLayout - Horizontal");
        frame.setSize(500, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        // Add horizontal spacing at start
        panel.add(Box.createHorizontalStrut(20));
        
        // Add buttons side by side
        panel.add(new JButton("First"));
        panel.add(Box.createHorizontalStrut(10));
        panel.add(new JButton("Second"));
        panel.add(Box.createHorizontalStrut(10));
        panel.add(new JButton("Third"));
        
        // Add flexible spacing
        panel.add(Box.createHorizontalGlue());
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Three buttons in a row with spacing
    }
}

// ============================================
// BOXLAYOUT EXAMPLE 3: Vertical Form
// ============================================
class BoxExample3_VerticalForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Form - BoxLayout");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        
        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userLabel);
        
        JTextField userField = new JTextField(15);
        userField.setMaximumSize(userField.getPreferredSize());
        panel.add(userField);
        panel.add(Box.createVerticalStrut(15));
        
        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(passLabel);
        
        JPasswordField passField = new JPasswordField(15);
        passField.setMaximumSize(passField.getPreferredSize());
        panel.add(passField);
        panel.add(Box.createVerticalStrut(20));
        
        // Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loginBtn);
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Centered vertical form with proper spacing
    }
}

// ============================================
// BOXLAYOUT EXAMPLE 4: Mixed Layout
// ============================================
class BoxExample4_MixedLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mixed BoxLayout");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main horizontal panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        
        // Left vertical panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        leftPanel.add(new JButton("Home"));
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(new JButton("Settings"));
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(new JButton("About"));
        leftPanel.add(Box.createVerticalGlue());
        
        // Right vertical panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Content"));
        rightPanel.add(new JLabel("Item 1"));
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(new JLabel("Item 2"));
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(new JLabel("Item 3"));
        
        // Add both panels to main
        mainPanel.add(leftPanel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(rightPanel);
        
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // OUTPUT: Menu on left, content on right
        // Both are vertical BoxLayouts inside horizontal BoxLayout
    }
}