/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { SuchAzUserSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz-dialog.component';
import { SuchAzUserSuchazService } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz.service';
import { SuchAzUserSuchaz } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz.model';
import { TraitSuchazService } from '../../../../../../main/webapp/app/entities/trait-suchaz';
import { UserProfileSuchazService } from '../../../../../../main/webapp/app/entities/user-profile-suchaz';
import { WishListSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-suchaz';
import { BuyLaterListSuchazService } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz';
import { ActivityListSuchazService } from '../../../../../../main/webapp/app/entities/activity-list-suchaz';

describe('Component Tests', () => {

    describe('SuchAzUserSuchaz Management Dialog Component', () => {
        let comp: SuchAzUserSuchazDialogComponent;
        let fixture: ComponentFixture<SuchAzUserSuchazDialogComponent>;
        let service: SuchAzUserSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [SuchAzUserSuchazDialogComponent],
                providers: [
                    TraitSuchazService,
                    UserProfileSuchazService,
                    WishListSuchazService,
                    BuyLaterListSuchazService,
                    ActivityListSuchazService,
                    SuchAzUserSuchazService
                ]
            })
            .overrideTemplate(SuchAzUserSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SuchAzUserSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuchAzUserSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SuchAzUserSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.suchAzUser = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'suchAzUserListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SuchAzUserSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.suchAzUser = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'suchAzUserListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
