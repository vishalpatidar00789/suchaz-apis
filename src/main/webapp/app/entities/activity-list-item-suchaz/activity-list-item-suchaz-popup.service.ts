import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ActivityListItemSuchaz } from './activity-list-item-suchaz.model';
import { ActivityListItemSuchazService } from './activity-list-item-suchaz.service';

@Injectable()
export class ActivityListItemSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private activityListItemService: ActivityListItemSuchazService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.activityListItemService.find(id)
                    .subscribe((activityListItemResponse: HttpResponse<ActivityListItemSuchaz>) => {
                        const activityListItem: ActivityListItemSuchaz = activityListItemResponse.body;
                        this.ngbModalRef = this.activityListItemModalRef(component, activityListItem);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.activityListItemModalRef(component, new ActivityListItemSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    activityListItemModalRef(component: Component, activityListItem: ActivityListItemSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.activityListItem = activityListItem;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
