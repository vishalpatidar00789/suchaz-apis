import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ActivityListSuchaz } from './activity-list-suchaz.model';
import { ActivityListSuchazPopupService } from './activity-list-suchaz-popup.service';
import { ActivityListSuchazService } from './activity-list-suchaz.service';
import { SuchAzUserSuchaz, SuchAzUserSuchazService } from '../such-az-user-suchaz';

@Component({
    selector: 'jhi-activity-list-suchaz-dialog',
    templateUrl: './activity-list-suchaz-dialog.component.html'
})
export class ActivityListSuchazDialogComponent implements OnInit {

    activityList: ActivityListSuchaz;
    isSaving: boolean;

    suchazusers: SuchAzUserSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private activityListService: ActivityListSuchazService,
        private suchAzUserService: SuchAzUserSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.suchAzUserService
            .query({filter: 'activitylist-is-null'})
            .subscribe((res: HttpResponse<SuchAzUserSuchaz[]>) => {
                if (!this.activityList.suchAzUserId) {
                    this.suchazusers = res.body;
                } else {
                    this.suchAzUserService
                        .find(this.activityList.suchAzUserId)
                        .subscribe((subRes: HttpResponse<SuchAzUserSuchaz>) => {
                            this.suchazusers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.activityList.id !== undefined) {
            this.subscribeToSaveResponse(
                this.activityListService.update(this.activityList));
        } else {
            this.subscribeToSaveResponse(
                this.activityListService.create(this.activityList));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ActivityListSuchaz>>) {
        result.subscribe((res: HttpResponse<ActivityListSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ActivityListSuchaz) {
        this.eventManager.broadcast({ name: 'activityListListModification', content: 'OK'});
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
    selector: 'jhi-activity-list-suchaz-popup',
    template: ''
})
export class ActivityListSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private activityListPopupService: ActivityListSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.activityListPopupService
                    .open(ActivityListSuchazDialogComponent as Component, params['id']);
            } else {
                this.activityListPopupService
                    .open(ActivityListSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
