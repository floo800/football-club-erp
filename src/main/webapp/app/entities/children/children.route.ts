import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IChildren, Children } from 'app/shared/model/children.model';
import { ChildrenService } from './children.service';
import { ChildrenComponent } from './children.component';
import { ChildrenDetailComponent } from './children-detail.component';
import { ChildrenUpdateComponent } from './children-update.component';

@Injectable({ providedIn: 'root' })
export class ChildrenResolve implements Resolve<IChildren> {
  constructor(private service: ChildrenService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IChildren> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((children: HttpResponse<Children>) => {
          if (children.body) {
            return of(children.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Children());
  }
}

export const childrenRoute: Routes = [
  {
    path: '',
    component: ChildrenComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.children.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ChildrenDetailComponent,
    resolve: {
      children: ChildrenResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.children.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ChildrenUpdateComponent,
    resolve: {
      children: ChildrenResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.children.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ChildrenUpdateComponent,
    resolve: {
      children: ChildrenResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'footballClubErpApp.children.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
