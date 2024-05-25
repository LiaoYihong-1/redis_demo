import React, {useState} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Select from '@material-ui/core/Select';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import $ from "jquery";
import {createBrowserHistory} from "history";
import ButtonBase from "@material-ui/core/ButtonBase";
import loginImage from "../../assets/images/login-1.jpeg";
import clsx from "clsx";
import FilledInput from "@material-ui/core/FilledInput";
import InputAdornment from "@material-ui/core/InputAdornment";
import AccountCircle from "@material-ui/icons/AccountCircle";
import EmailIcon from '@material-ui/icons/Email';
import IconButton from "@material-ui/core/IconButton";
import Visibility from "@material-ui/icons/Visibility";
import VisibilityOff from "@material-ui/icons/VisibilityOff";
import backgroundImage from "../../assets/images/login-2.jpg";



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
export default function SignUp() {
    const classes = useStyles();
    const [values, setValues] = useState({
        password: '',
        username: '',
        email: ''
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
    function sendAccount(){
        // alert(values.username+" "+ values.email + " " +values.password)
        $.ajax({
                url: "api/user/register",
                method:"POST",
                contentType: 'application/json',
                data:JSON.stringify({
                    password: values.password,
                    name: values.username,
                    email: values.email,
                }),
                async:false,
                success:function (res){
                    const history = createBrowserHistory();
                    history.push('/videos');
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
                    <Grid item>
                        <FormControl className={clsx(classes.margin, classes.textField)} variant="filled">
                            <InputLabel className={classes.blueWhite} htmlFor="filled-adornment-username">User</InputLabel>
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
                    <Grid item>
                        <FormControl className={clsx(classes.margin, classes.textField)} variant="filled">
                            <InputLabel className={classes.blueWhite} htmlFor="filled-adornment-username">Email</InputLabel>
                            <FilledInput
                                style={{ backgroundColor: '#a9d3fa', color: '#fff' }}
                                id="filled-adornment-username"
                                value={values.email}
                                onChange={handleChange('email')}
                                endAdornment={<InputAdornment position="end"><EmailIcon/></InputAdornment>}
                                aria-describedby="filled-weight-helper-text"
                                inputProps={{
                                    'aria-label': 'Email',
                                }}
                            />
                        </FormControl>
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
                            <Button style={{ backgroundColor: '#57a8f1', color: '#fff' }} variant={"contained"} onClick={()=>sendAccount()}>Rigster</Button>
                        </FormControl>
                    </Grid>
                </form>
            </Grid>
        </Container>
    );
}