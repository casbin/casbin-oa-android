import * as React from 'react';
import {createStackNavigator} from 'react-navigation-stack';
import {createAppContainer, createSwitchNavigator} from 'react-navigation';
import AuthLoadingScreen from '../pages/minePage/AuthLoadingScreen';
import LoginHome from '../pages/minePage/LoginHome';
import LoginPage from '../pages/minePage/LoginPage';
import MinePage from '../pages/minePage/MinePage';

// if has logged in, show mine page with personal info
const MineStack = createStackNavigator({Mine: MinePage});

// if need to login, show login page
const LoginStack = createStackNavigator({
  LoginHome: LoginHome, // some public info & btn to login
  LoginPage: LoginPage, // webview with casdoor
});

export default createAppContainer(
  createSwitchNavigator(
    {
      AuthLoading: AuthLoadingScreen, // judge if logged in to navigate to Mine or Login
      Mine: MineStack,
      Login: LoginStack,
    },
    {
      initialRouteName: 'AuthLoading',
    },
  ),
);
