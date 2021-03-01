# Save the Puppy!


![Workflow result](https://github.com/McSnowflake/android-dev-challenge-compose/workflows/Check/badge.svg)


## :scroll: Description
It's a small (mockup) app that helps you find a new puppy companion. It shows a list where you can filter by traits and select your favorites until the final decision. By clicking on a puppy card, you get a detail screen with more photos and a more ... details :)

Photo links are from [www.pexels.com](https://www.pexels.com).

## :bulb: Motivation and Context
This is my submission to the [Jetpack Compose Dev Challenge](https://developer.android.com/dev-challenge). It was learning by doing for me and not all is perfect yet (with my app; compose is pretty amazing despite being in beta).

Theming (incl. dark mode) was a breeze. It's nice how compose directly translates to material design.

I had a harder time figuring out navigation and state handling. I ended up using MutableState directly in the ViewModel. LiveDate and Flow didn't want to play nice.
Also I used routing, which worked, but I couldn't figure out how to animate transitions that way.


## :camera_flash: Screenshots
<img src="/results/screenshot_1.png" width="260">&emsp;<img src="/results/screenshot_2.png" width="260">;<img src="/results/screenshot_3.png" width="260">;<img src="/results/screenshot_4.png" width="260">

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```