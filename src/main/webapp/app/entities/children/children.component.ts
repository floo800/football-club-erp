import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IChildren } from 'app/shared/model/children.model';
import { ChildrenService } from './children.service';
import { ChildrenDeleteDialogComponent } from './children-delete-dialog.component';

@Component({
  selector: 'jhi-children',
  templateUrl: './children.component.html',
})
export class ChildrenComponent implements OnInit, OnDestroy {
  children?: IChildren[];
  eventSubscriber?: Subscription;

  constructor(
    protected childrenService: ChildrenService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.childrenService.query().subscribe((res: HttpResponse<IChildren[]>) => (this.children = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInChildren();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IChildren): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInChildren(): void {
    this.eventSubscriber = this.eventManager.subscribe('childrenListModification', () => this.loadAll());
  }

  delete(children: IChildren): void {
    const modalRef = this.modalService.open(ChildrenDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.children = children;
  }
}
