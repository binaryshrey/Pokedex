import React from "react";
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Typography from "@mui/material/Typography";
import db from "../data/db.json";
import GitHubIcon from '@mui/icons-material/GitHub';
import ShopRoundedIcon from '@mui/icons-material/ShopRounded';



const Desktoplayout = () => {

	return (
		<div style={{ backgroundImage: 'url(https://cdn.jsdelivr.net/gh/binaryshrey/Pokedex@main/static/bg.png)', backgroundSize: 'cover', backgroundPosition: 'center', backgroundRepeat: 'no-repeat', width:'100vw',height:'100vh'}}>
			<div style={{padding:'1rem', display: 'flex', justifyContent:'center'}}>
				<Stack spacing={2} direction="row" >
					<Button variant="text" size="small" sx={{color:"white", fontSize:'12px'}}>Home</Button>
					<Button variant="text" size="small" sx={{color:"white", fontSize:'12px'}}>Privacy</Button>
					<Button variant="text" size="small" sx={{color:"white", fontSize:'12px'}}>Terms</Button>
					<Button variant="text" size="small" sx={{color:"white", fontSize:'12px'}}>Contact</Button>
				</Stack>
			</div>
			<div style={{border:'0.5px solid #2D2d2d'}}/>
			<div style={{marginTop:'4rem', display: 'flex', justifyContent:'center'}}>
				<img alt="play_store" src="logo.png" height="50rem"  />
			</div>
			<div style={{marginTop:'2rem', display: 'flex', justifyContent:'center'}}>
				<Typography variant="h2" component="div" fontWeight={500} >
						{db.appName}
				</Typography>
			</div>
			<div style={{marginTop:'1rem', display: 'flex', justifyContent:'center'}}>
				<Typography variant="body1" component="div" fontWeight={500} >
						{db.description}
				</Typography>
			</div>
			<div style={{marginTop:'0rem', display: 'flex', justifyContent:'center'}}>
				<Typography variant="body1" component="div" fontWeight={500} >
						{db.description_next}
				</Typography>
			</div>
			<div style={{marginTop:'2rem', display: 'flex', justifyContent:'center'}}>
				<Stack spacing={2} direction="row">
					<Button variant="contained" size="large" startIcon={<ShopRoundedIcon/>} sx={{backgroundColor:'white', '&:hover': {backgroundColor: '#d0d0d0', color: 'black', }}}  style={{textTransform: 'none'}}>Download</Button>
					<Button variant="outlined" size="large" startIcon={<GitHubIcon/>} sx={{borderColor:'white',color:'white' ,'&:hover': {borderColor:'#d0d0d0',color:'white' }}} style={{textTransform: 'none'}}>Github</Button>
				</Stack>
			</div>
		</div>
	);
};

export default Desktoplayout;
