import * as React from 'react';
import {createBottomTabNavigator} from 'react-navigation-tabs';
import {createAppContainer} from 'react-navigation';
import HomeTab from './src/TabPages/HomeTab';
import MineTab from './src/TabPages/MineTab';

const AppNav = createBottomTabNavigator(
  {
    Home: {
      screen: HomeTab,
    },
    Mine: {
      screen: MineTab,
    },
  },
  {
    tabBarOptions: {
      activeBackgroundColor: '#ffffff',
      inactiveBackgroundColor: '#ffffff',
      activeTintColor: '#1072ea',
      inactiveTintColor: '#95b3e0',
      labelStyle: {
        fontSize: 12,
      },
      style: {
        backgroundColor: 'blue',
      },
    },
  },
);

export default createAppContainer(AppNav);
