import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ConsumerProfileSuchaz } from './consumer-profile-suchaz.model';
import { ConsumerProfileSuchazPopupService } from './consumer-profile-suchaz-popup.service';
import { ConsumerProfileSuchazService } from './consumer-profile-suchaz.service';
import { SuchAzUserSuchaz, SuchAzUserSuchazService } from '../such-az-user-suchaz';

@Component({
    selector: 'jhi-consumer-profile-suchaz-dialog',
    templateUrl: './consumer-profile-suchaz-dialog.component.html'
})
export class ConsumerProfileSuchazDialogComponent implements OnInit {

    consumerProfile: ConsumerProfileSuchaz;
    isSaving: boolean;

    suchazusers: SuchAzUserSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private consumerProfileService: ConsumerProfileSuchazService,
        private suchAzUserService: SuchAzUserSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.suchAzUserService.query()
            .subscribe((res: HttpResponse<SuchAzUserSuchaz[]>) => { this.suchazusers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.consumerProfile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.consumerProfileService.update(this.consumerProfile));
        } else {
            this.subscribeToSaveResponse(
                this.consumerProfileService.create(this.consumerProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ConsumerProfileSuchaz>>) {
        result.subscribe((res: HttpResponse<ConsumerProfileSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ConsumerProfileSuchaz) {
        this.eventManager.broadcast({ name: 'consumerProfileListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSuchAzUserById(index: number, item: SuchAzUserSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-consumer-profile-suchaz-popup',
    template: ''
})
export class ConsumerProfileSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consumerProfilePopupService: ConsumerProfileSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.consumerProfilePopupService
                    .open(ConsumerProfileSuchazDialogComponent as Component, params['id']);
            } else {
                this.consumerProfilePopupService
                    .open(ConsumerProfileSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
