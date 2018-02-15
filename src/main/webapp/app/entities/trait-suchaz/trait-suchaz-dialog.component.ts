import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TraitSuchaz } from './trait-suchaz.model';
import { TraitSuchazPopupService } from './trait-suchaz-popup.service';
import { TraitSuchazService } from './trait-suchaz.service';
import { SuchAzUserSuchaz, SuchAzUserSuchazService } from '../such-az-user-suchaz';

@Component({
    selector: 'jhi-trait-suchaz-dialog',
    templateUrl: './trait-suchaz-dialog.component.html'
})
export class TraitSuchazDialogComponent implements OnInit {

    trait: TraitSuchaz;
    isSaving: boolean;

    suchazusers: SuchAzUserSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private traitService: TraitSuchazService,
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
        if (this.trait.id !== undefined) {
            this.subscribeToSaveResponse(
                this.traitService.update(this.trait));
        } else {
            this.subscribeToSaveResponse(
                this.traitService.create(this.trait));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TraitSuchaz>>) {
        result.subscribe((res: HttpResponse<TraitSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TraitSuchaz) {
        this.eventManager.broadcast({ name: 'traitListModification', content: 'OK'});
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

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-trait-suchaz-popup',
    template: ''
})
export class TraitSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traitPopupService: TraitSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.traitPopupService
                    .open(TraitSuchazDialogComponent as Component, params['id']);
            } else {
                this.traitPopupService
                    .open(TraitSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
