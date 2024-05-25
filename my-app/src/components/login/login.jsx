import React,{useState} from 'react';
import Container from '@material-ui/core/Container';
import $ from 'jquery';
import { makeStyles } from '@material-ui/core/styles';
import {createBrowserHistory} from "history";
import Box from '@material-ui/core/Box';
import Grid from '@material-ui/core/Grid';
import ButtonBase from '@material-ui/core/ButtonBase';
import loginImage from '../../assets/images/login-1.jpeg';
import AccountCircle from '@material-ui/icons/AccountCircle';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import FilledInput from '@material-ui/core/FilledInput';
import clsx from 'clsx';
import IconButton from '@material-ui/core/IconButton';
import InputAdornment from '@material-ui/core/InputAdornment';
import backgroundImage from '../../assets/images/login-2.jpg'
import Button from '@material-ui/core/Button';
import Link from "@material-ui/core/Link";

// 创建自定义主题

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
    }
}));
export default function LoginPage() {
    const classes = useStyles();
    const [values, setValues] = useState({
        password: '',
        username: '',
        email:''
    });

    const handleChange = (prop) => (event) => {
        setValues({ ...values, [prop]: event.target.value });
    };

    const handleClickShowPassword = () => {
        setValues({ ...values, showPassword: !values.showPassword });
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };
    function sendAccount(username,password){
        $.ajax({
                url: "api/user/login?password="+password+"&username="+username,
                method:"GET",
                headers: {
                    'Content-Type': 'application/json'
                },
                async:false,
                success:function (res){
                    window.sessionStorage.setItem("token",res.token);
                    const history = createBrowserHistory();
                    history.push('/main');
                },
                statusCode:{
                    404:function (){
                        alert("No this account")
                    },
                    400:function (){
                        alert("Make sure that you input an email")
                    }
                }
            }
        );
    }

    return (
        <Container className={classes.background} >
            <Grid item container
                  direction="row"
                  justifyContent="center"
                  alignItems="center">
                <ButtonBase  className={classes.image}>
                    <img className={classes.img} alt="complex" src={loginImage} />
                </ButtonBase>
            </Grid>
            <Grid item container
                  direction="row"
                  justifyContent="center"
                  alignItems="center">
                <form>
                    <Grid container spacing={1} alignItems="flex-end">
                        <Grid item>
                            <FormControl className={clsx(classes.margin, classes.textField)} variant="filled">
                                <InputLabel className={classes.blueWhite} htmlFor="filled-adornment-username">Email</InputLabel>
                                    <FilledInput
                                        style={{ backgroundColor: '#a9d3fa', color: '#fff' }}
                                        id="filled-adornment-username"
                                        value={values.username}
                                        onChange={handleChange('username')}
                                        endAdornment={<InputAdornment position="end"><AccountCircle/></InputAdornment>}
                                        aria-describedby="filled-weight-helper-text"
                                        inputProps={{
                                            'aria-label': 'User',
                                        }}
                                    />
                            </FormControl>
                        </Grid>
                    </Grid>
                    <Grid item xs={12}>
                        <FormControl className={clsx(classes.margin, classes.textField)} variant="filled">
                            <InputLabel style={{color: '#fff' }} htmlFor="filled-adornment-password">Password</InputLabel>
                            <FilledInput
                                style={{ backgroundColor: '#a9d3fa', color: '#fff' }}
                                id="filled-adornment-password"
                                type={values.showPassword ? 'text' : 'password'}
                                value={values.password}
                                onChange={handleChange('password')}
                                endAdornment={
                                    <InputAdornment position="end">
                                        <IconButton
                                            aria-label="toggle password visibility"
                                            style={{color: '#fff' }}
                                            onClick={handleClickShowPassword}
                                            onMouseDown={handleMouseDownPassword}
                                            edge="end"
                                        >
                                            {values.showPassword ? <Visibility /> : <VisibilityOff />}
                                        </IconButton>
                                    </InputAdornment>
                                }
                            />
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <FormControl className={clsx(classes.margin, classes.textField)} variant="filled">
                            <Button style={{ backgroundColor: '#57a8f1', color: '#fff' }} variant={"contained"} onClick={()=>sendAccount()}>Login</Button>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12} justifyContent={"center"}>
                        <Link href="/register" variant="body2">
                            <Box fontSize={12} className={classes.link}>
                                {"Don't have an account? Sign Up"}
                            </Box>
                        </Link>
                    </Grid>
                </form>
            </Grid>
        </Container>
    );
}