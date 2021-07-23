import * as React from 'react';
import {Text, View} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default class HomePage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      token: '',
    };
  }

  componentDidMount() {}

  render() {
    return (
      <View>
        <Text>Home</Text>
      </View>
    );
  }
}
