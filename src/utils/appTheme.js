import { createTheme } from "@mui/material/styles"

const darkTheme = createTheme({
  palette: {
    mode: "dark",
    background: {
      default: "#000000",
    },
    typography: {
    "fontFamily": `"BlinkMacSystemFont", Helvetica Neue", "Roboto", "sans-serif"`
  },
  secondary: {
      main: "#ffffff",
    },
  },
})

export default darkTheme