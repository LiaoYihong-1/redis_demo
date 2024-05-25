import React from 'react';
import ReactPlayer from 'react-player';
import Grid from "@material-ui/core/Grid";
import Container from "@material-ui/core/Container";
import {makeStyles} from "@material-ui/core/styles";
import backgroundImage from "../../assets/images/login-2.jpg";
import SearchVideoBar from "./menu";
const useStyles = makeStyles((theme) => ({
    blueWhite:{
        backgroundColor: '#a9d3fa',
        color: '#fff'
    },
    paper: {
        margin: theme.spacing(8, 4),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    link:{
        marginLeft: "10px"
    },
    image: {
        width: 80,
        height: 80,
    },
    img: {
        margin: 'auto',
        display: 'block',
        maxWidth: '100%',
        maxHeight: '100%',
    },
    margin: {
        margin: theme.spacing(1),
    },
    withoutLabel: {
        marginTop: theme.spacing(3),
    },
    textField: {
        width: '25ch',
    },
    background:{
        backgroundImage: `url(${backgroundImage})`,
        backgroundRepeat: 'no-repeat',
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        height: "100vh",
        width: "100%"
    },
    mid:{
        justifyContent:"center",
        alignItems: "center"
    },
    root:{
        width:"100%",
        height:"100vh"
    }
}));
const VideoPlayer = () => {
    const classes = useStyles()
    return (
        <Container className={classes.root}>
            <SearchVideoBar/>
            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
            >
                <div className={classes.mid}>
                    <ReactPlayer
                        url='api/videos/test' // Replace with your actual video URL
                        controls={true}
                    />
                </div>
            </Grid>
        </Container>
    );
}

export default VideoPlayer;
