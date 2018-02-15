import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ConsumerProfileSuchaz } from './consumer-profile-suchaz.model';
import { ConsumerProfileSuchazService } from './consumer-profile-suchaz.service';

@Injectable()
export class ConsumerProfileSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private consumerProfileService: ConsumerProfileSuchazService

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
                this.consumerProfileService.find(id)
                    .subscribe((consumerProfileResponse: HttpResponse<ConsumerProfileSuchaz>) => {
                        const consumerProfile: ConsumerProfileSuchaz = consumerProfileResponse.body;
                        this.ngbModalRef = this.consumerProfileModalRef(component, consumerProfile);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.consumerProfileModalRef(component, new ConsumerProfileSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    consumerProfileModalRef(component: Component, consumerProfile: ConsumerProfileSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.consumerProfile = consumerProfile;
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
