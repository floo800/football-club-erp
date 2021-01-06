import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { FootballClubErpSharedModule } from 'app/shared/shared.module';
import { FootballClubErpCoreModule } from 'app/core/core.module';
import { FootballClubErpAppRoutingModule } from './app-routing.module';
import { FootballClubErpHomeModule } from './home/home.module';
import { FootballClubErpEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    FootballClubErpSharedModule,
    FootballClubErpCoreModule,
    FootballClubErpHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    FootballClubErpEntityModule,
    FootballClubErpAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class FootballClubErpAppModule {}
