/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { UserGiftWrapperSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz-dialog.component';
import { UserGiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.service';
import { UserGiftWrapperSuchaz } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.model';
import { SuchAzUserSuchazService } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz';
import { GiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz';

describe('Component Tests', () => {

    describe('UserGiftWrapperSuchaz Management Dialog Component', () => {
        let comp: UserGiftWrapperSuchazDialogComponent;
        let fixture: ComponentFixture<UserGiftWrapperSuchazDialogComponent>;
        let service: UserGiftWrapperSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [UserGiftWrapperSuchazDialogComponent],
                providers: [
                    SuchAzUserSuchazService,
                    ItemSuchazService,
                    GiftWrapperSuchazService,
                    UserGiftWrapperSuchazService
                ]
            })
            .overrideTemplate(UserGiftWrapperSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserGiftWrapperSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserGiftWrapperSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserGiftWrapperSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userGiftWrapper = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userGiftWrapperListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserGiftWrapperSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userGiftWrapper = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userGiftWrapperListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
