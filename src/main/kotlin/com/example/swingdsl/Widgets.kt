import javax.swing.*

fun JPanel.textField(placeholder: String = "", init: JTextField.() -> Unit = {}): JTextField {
    val textField = JTextField(placeholder)
    textField.init()
    this.add(textField)
    return textField
}

fun JPanel.button(text: String = "", init: JButton.() -> Unit = {}): JButton {
    val button = JButton(text)
    button.init()
    this.add(button)
    return button
}

fun JPanel.checkBox(text: String = "", init: JCheckBox.() -> Unit = {}): JCheckBox {
    val checkBox = JCheckBox(text)
    checkBox.init()
    this.add(checkBox)
    return checkBox
}

fun JPanel.radioButton(text: String = "", init: JRadioButton.() -> Unit = {}): JRadioButton {
    val radioButton = JRadioButton(text)
    radioButton.init()
    this.add(radioButton)
    return radioButton
}

fun JPanel.comboBox(items: Array<String>, init: JComboBox<String>.() -> Unit = {}): JComboBox<String> {
    val comboBox = JComboBox(items)
    comboBox.init()
    this.add(comboBox)
    return comboBox
}

fun JPanel.spinner(value: Int, init: JSpinner.() -> Unit = {}): JSpinner {
    val spinner = JSpinner(SpinnerNumberModel(value, 0, 100, 1))
    spinner.init()
    this.add(spinner)
    return spinner
}

fun JPanel.slider(min: Int, max: Int, init: JSlider.() -> Unit = {}): JSlider {
    val slider = JSlider(min, max)
    slider.init()
    this.add(slider)
    return slider
}

fun JPanel.textArea(rows: Int = 5, columns: Int = 20, init: JTextArea.() -> Unit = {}): JTextArea {
    val textArea = JTextArea(rows, columns)
    textArea.init()
    this.add(JScrollPane(textArea))
    return textArea
}

fun JPanel.passwordField(placeholder: String = "", init: JPasswordField.() -> Unit = {}): JPasswordField {
    val passwordField = JPasswordField(placeholder)
    passwordField.init()
    this.add(passwordField)
    return passwordField
}

fun JPanel.label(text: String, init: JLabel.() -> Unit = {}): JLabel {
    val label = JLabel(text)
    label.init()
    this.add(label)
    return label
}