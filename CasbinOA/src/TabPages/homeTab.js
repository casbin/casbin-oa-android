import * as React from 'react';
import {Text, View} from 'react-native';
import HomePage from '../pages/homePage/HomePage';

export default class HomeTab extends React.Component {
  render() {
    return (
      <View>
        <Text>Home Tab</Text>
        <HomePage />
      </View>
    );
  }
}
