import React from "react";
import Terms from "./Terms";
import darkTheme from "../utils/appTheme";
import { useMediaQuery } from "react-responsive"
import CssBaseline from "@mui/material/CssBaseline";
import { ThemeProvider } from "@mui/material/styles";



const TermsContainer = () => {

    const isDesktop = useMediaQuery({
		query: "(min-width: 1001px)",
	})

    const isMobile = useMediaQuery({
    	query: "(max-width: 1000px)",
  	})


    return(
        <>
            <ThemeProvider theme={darkTheme}>
                    <CssBaseline />
                    {isDesktop && <Terms/>}
			        {isMobile && <Terms/>}
            </ThemeProvider>
        </>
    )
}

export default TermsContainer