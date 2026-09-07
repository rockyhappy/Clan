package com.devrachit.clan.presentation.screens.main

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.devrachit.clan.presentation.screens.main.components.ClanBottomBar
import com.devrachit.clan.presentation.screens.main.components.ClanDrawer
import com.devrachit.clan.presentation.screens.main.components.DashboardTab
import com.devrachit.clan.presentation.theme.ClanTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ClanDashboardWrapper(
    username: String = "Chief",
    onToggleTheme: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val viewModel: DashboardViewModel = hiltViewModel()

    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    
    // The max drawer width
    val drawerWidth = (screenWidth * 0.7f).coerceAtMost(320.dp)
    val drawerWidthPx = with(androidx.compose.ui.platform.LocalDensity.current) { drawerWidth.toPx() }

    // State for tracking the drawer open/close translation
    val translationX = remember { Animatable(0f) }
    translationX.updateBounds(0f, drawerWidthPx)
    
    // Calculate progress 0f -> 1f
    val drawerProgress = if (drawerWidthPx > 0) translationX.value / drawerWidthPx else 0f

    var currentTab by remember { mutableStateOf(DashboardTab.VILLAGE) }

    val closeDrawer = {
        coroutineScope.launch {
            translationX.animateTo(
                0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
    }

    val toggleDrawer = {
        coroutineScope.launch {
            if (translationX.value > 0f) {
                // Close
                translationX.animateTo(
                    0f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            } else {
                // Open
                translationX.animateTo(
                    drawerWidthPx,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
        }
    }

    val draggableState = rememberDraggableState { delta ->
        coroutineScope.launch {
            translationX.snapTo((translationX.value + delta).coerceIn(0f, drawerWidthPx))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClanTheme.colors.surfaceContainer)
    ) {
        // --- LAYER 1: DRAWER ---
        ClanDrawer(
            username = username,
            onClose = { closeDrawer() },
            onLogout = { viewModel.logout(onSuccess = onLogout) },
            modifier = Modifier
                .offset { 
                    // Drawer slides in slightly from the left as it opens
                    androidx.compose.ui.unit.IntOffset(
                        x = lerp(-100f, 0f, drawerProgress).roundToInt(),
                        y = 0
                    )
                }
        )

        // --- LAYER 2: MAIN CONTENT WITH TRANSFORMATION ---
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    this.translationX = translationX.value
                    val scale = lerp(1f, 0.85f, drawerProgress)
                    this.scaleX = scale
                    this.scaleY = scale
                    this.shape = RoundedCornerShape(lerp(0f, 32f, drawerProgress).dp)
                    this.clip = drawerProgress > 0f
                    this.shadowElevation = drawerProgress * 20f
                }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        coroutineScope.launch {
                            val target = if (translationX.value > drawerWidthPx / 2f || velocity > 500f) {
                                drawerWidthPx
                            } else {
                                0f
                            }
                            translationX.animateTo(
                                target,
                                initialVelocity = velocity,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessMediumLow
                                )
                            )
                        }
                    }
                )
                .background(ClanTheme.colors.background)
        ) {
            
            // Tab Content
            when(currentTab) {
                DashboardTab.VILLAGE -> ClanDashboardScreen(
                    onToggleTheme = onToggleTheme,
                    onLogout = { viewModel.logout(onSuccess = onLogout) },
                    onMenuClick = { toggleDrawer() }
                )
                DashboardTab.WARS -> { /* TODO: Wars Screen */ }
                DashboardTab.LAB -> { /* TODO: Lab Screen */ }
            }

            // --- LAYER 3: FLOATING BOTTOM BAR ---
            // Bottom bar hides (offsets down) as drawer opens
            val bottomBarYOffset = lerp(0f, 150f, drawerProgress)
            val bottomBarScale = lerp(1f, 0.8f, drawerProgress)

            ClanBottomBar(
                currentTab = currentTab,
                onTabSelected = { currentTab = it },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp, start = 24.dp, end = 24.dp)
                    .offset(y = bottomBarYOffset.dp)
                    .scale(bottomBarScale)
            )
        }
    }
}
