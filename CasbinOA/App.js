import * as React from 'react';
import {createBottomTabNavigator} from 'react-navigation-tabs';
import {createAppContainer} from 'react-navigation';
import homeTab from './src/TabPages/homeTab';
import mineTab from './src/TabPages/mineTab';

const AppNav = createBottomTabNavigator(
  {
    Home: {
      screen: homeTab,
    },
    Mine: {
      screen: mineTab,
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
