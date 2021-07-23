import * as React from 'react';
import {Text, View} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default class MinePage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      token: '',
    };
  }

  componentDidMount() {
    this._getUserToken().then();
  }

  _getUserToken = async () => {
    const userToken = await AsyncStorage.getItem('userToken');
    this.setState({token: userToken});
  };

  render() {
    return (
      <View>
        <Text>Mine Page</Text>
        <Text>{this.state.token}</Text>
      </View>
    );
  }
}
